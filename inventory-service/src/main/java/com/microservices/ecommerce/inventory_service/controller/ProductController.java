package com.microservices.ecommerce.inventory_service.controller;

import com.microservices.ecommerce.inventory_service.dto.ProductDto;
import com.microservices.ecommerce.inventory_service.entity.Product;
import com.microservices.ecommerce.inventory_service.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> getProducts = productService.getAllInventory();
        return ResponseEntity.ok(getProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        ProductDto productDetilas = productService.getProductById(id);
        return  ResponseEntity.ok(productDetilas);
    }
}
