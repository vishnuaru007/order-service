package com.aru.orderservice.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aru.orderservice.models.Order;
import com.aru.orderservice.repositories.OrderRepository;
import com.aru.orderservice.services.defination.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createAndTriggerOrder(Order order) {
        orderRepository.save(order);
        System.out.println("Triggering order processing workflow...");
    }

}
