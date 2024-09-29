package com.aru.orderservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerEmail;

    @ElementCollection
    private List<Product> products;

}

@Data
@Embeddable
class Product {
    private Long productId;
    private int quantity;
}
