package com.aru.orderservice.models.dto;

import java.util.List;

import com.aru.orderservice.models.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private Customer customer;
    private List<Product> products;

}
