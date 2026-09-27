package com.microservices.ecommerce.inventory_service.service;

import com.microservices.ecommerce.inventory_service.dto.OrderRequestDto;
import com.microservices.ecommerce.inventory_service.dto.OrderRequestItemDto;
import com.microservices.ecommerce.inventory_service.dto.ProductDto;
import com.microservices.ecommerce.inventory_service.entity.Product;
import com.microservices.ecommerce.inventory_service.reposotory.ProductRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllInventory() {
        log.info("Fetching All Inventory Items");
        List<Product> inventories = productRepo.findAll();
        return inventories.stream().
                map(product -> modelMapper.map(product, ProductDto.class)).toList();
    }

    public ProductDto getProductById(Long id) {
        log.info("Getting Product By id : " + id);
        Optional<Product> productDetails = productRepo.findById(id);
        return productDetails
                .map(items -> modelMapper.map(items, ProductDto.class))
                .orElseThrow(
                        () -> new RuntimeException("Inventory Not Found By this Id:" + id));
    }

    @Transactional
    public Double reduceStock(OrderRequestDto orderRequestDto) {
        log.info("Requesing Stock");
        Double totalprice = 0.0;
        for (OrderRequestItemDto orderRequestItemDto : orderRequestDto.getItems()) {
            Long productId = orderRequestItemDto.getProductId();
            Integer productQuanity = orderRequestItemDto.getQuantity();

            Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product Not found"));

            if (product.getStocks() < productQuanity) {
                throw new RuntimeException("Product Cannot FullFilled for given Quanity");
            }
            product.setStocks(product.getStocks() - productQuanity);
            productRepo.save(product);
            totalprice = productQuanity * product.getPrice();
        }
        return totalprice;
    }
}
