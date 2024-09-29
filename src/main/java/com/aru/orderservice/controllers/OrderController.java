package com.aru.orderservice.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aru.orderservice.models.dto.OrderRequest;
import com.aru.orderservice.services.definations.OrderService;
import com.aru.orderservice.services.impl.ResponseEntityGenerator;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    ResponseEntityGenerator responseEntityGenerator;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Order placed successfully!");
        return responseEntityGenerator.generateSuccessResponse(response);
    }
}
