package com.microservices.ecommerce.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class OrderItems {

    @Id
    private Long id;

    private  Long productId;
    private Integer quantity;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Orders orders;
}
