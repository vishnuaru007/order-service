package com.aru.orderservice.services.definations;

import com.aru.orderservice.models.dto.OrderRequest;

public interface OrderService {
    public void placeOrder(OrderRequest orderRequest) ;
}
