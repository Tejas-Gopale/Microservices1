package com.microservices.ecommerce.order_service.client;

import com.microservices.ecommerce.order_service.dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service" ,path = "/inventory")
public interface InventoryOpenFIgnClient {

    @PutMapping("/products/reduce-stock")
    public Double reduceStock(@RequestBody OrderRequestDto orderRequestDto);
}
