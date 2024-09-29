package com.aru.orderservice.services.defination;

import com.aru.orderservice.models.Order;

public interface OrderService {
    public void createAndTriggerOrder(Order order);
}
