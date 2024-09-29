package com.aru.orderservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aru.orderservice.models.Order;
import com.aru.orderservice.services.defination.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        orderService.createAndTriggerOrder(order);
        return ResponseEntity.ok("Order placed successfully");
    }
}