package com.microservices.ecommerce.order_service.service;

import com.microservices.ecommerce.order_service.client.InventoryOpenFIgnClient;
import com.microservices.ecommerce.order_service.dto.OrderRequestDto;
import com.microservices.ecommerce.order_service.entity.OrderItems;
import com.microservices.ecommerce.order_service.entity.OrderStatus;
import com.microservices.ecommerce.order_service.entity.Orders;
import com.microservices.ecommerce.order_service.reposotry.OrderRepo;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServices {

    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;
    private final InventoryOpenFIgnClient inventoryOpenFIgnClient;
//    private final OrderItemRepo orderItemRepo;

    public List<OrderRequestDto> getAllOrders() {
        log.info("Fetching All orders : ");
        List<Orders> orders = orderRepo.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderRequestDto.class)).toList();
    }

    public OrderRequestDto getOrderById(Long id) {
        log.info("Fetching Orders : By id : {} " + id);
        Orders order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order Not Found"));
        return modelMapper.map(order, OrderRequestDto.class);
    }

    @Retry(name = "inventroyRetry", fallbackMethod = "createOrderFallBack")
    @RateLimiter(name = "inventoryRateLimiter" , fallbackMethod = "createOrderFallBack")
    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {

        Double totalPrice = inventoryOpenFIgnClient.reduceStock(orderRequestDto);

        Orders orders = modelMapper.map(orderRequestDto, Orders.class);

        for (OrderItems orderItems : orders.getItems()) {
            orderItems.setOrders(orders);

        }
        orders.setPrice(totalPrice);
        orders.setOrderStatus(OrderStatus.CONFIRUMEDORDER_STATUS);
        Orders orders1 = orderRepo.save(orders);

        return modelMapper.map(orders1, OrderRequestDto.class);
    }

    public OrderRequestDto createOrderFallBack(OrderRequestDto orderRequestDto, Throwable throwable) {
        log.info("Fall Back Deu to : " + throwable.getMessage());

        return new OrderRequestDto();
    }
}