package com.microservices.ecommerce.order_service.controller;

import com.microservices.ecommerce.order_service.dto.OrderRequestDto;
import com.microservices.ecommerce.order_service.service.OrderServices;
import lombok.AllArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

        private  final OrderServices orderServices;

        @GetMapping
        public ResponseEntity<List<OrderRequestDto>> getAllOrders(){
            List<OrderRequestDto> orders =  orderServices.getAllOrders();
            return  ResponseEntity.ok(orders);
        }

        @GetMapping("/{id}")
        public  ResponseEntity<OrderRequestDto> getOrderById(@PathVariable  Long id){
            OrderRequestDto orderRequestDto = orderServices.getOrderById(id);
            return  ResponseEntity.ok(orderRequestDto);
        }

}
