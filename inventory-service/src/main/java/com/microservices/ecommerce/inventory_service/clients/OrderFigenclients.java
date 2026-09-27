package com.microservices.ecommerce.inventory_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-service" , path = "/order")
public interface OrderFigenclients {

        @GetMapping("/core/helloOrders")
        public String helloOrders();

}
