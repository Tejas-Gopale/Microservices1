package com.microservices.ecommerce.order_service.service;

import com.microservices.ecommerce.order_service.dto.OrderRequestDto;
import com.microservices.ecommerce.order_service.entity.Orders;
import com.microservices.ecommerce.order_service.reposotry.OrderRepo;
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
        return  modelMapper.map(order, OrderRequestDto.class);
    }

}
