package com.microservices.ecommerce.inventory_service.reposotory;

import com.microservices.ecommerce.inventory_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {


}
