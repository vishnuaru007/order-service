package com.aru.orderservice.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aru.orderservice.models.Order;
import com.aru.orderservice.models.Product;
import com.aru.orderservice.models.dto.OrderRequest;
import com.aru.orderservice.repositories.OrderRepository;
import com.aru.orderservice.services.definations.OrderService;
import com.netflix.conductor.client.http.WorkflowClient;
import com.netflix.conductor.common.metadata.workflow.StartWorkflowRequest;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WorkflowClient conductorClient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomer().getName());
        order.setCustomerEmail(orderRequest.getCustomer().getEmail());
        List<String> productNames = orderRequest.getProducts().stream()
                .map(Product::getProduct)
                .collect(Collectors.toList());
        order.setProducts(productNames);
        order.setStatus("PENDING");
        Order savedOrder = orderRepository.save(order);

        startWorkflowRequest(productNames, savedOrder);
    }

    private void startWorkflowRequest(List<String> productNames, Order savedOrder) {
        Map<String, Object> input = new HashMap<>();
        input.put("orderId", savedOrder.getId());
        input.put("products", productNames);
        StartWorkflowRequest startWorkflowRequest = new StartWorkflowRequest();
        startWorkflowRequest.setName("order_processing_workflow");
        startWorkflowRequest.setVersion(1);
        startWorkflowRequest.setInput(input);
        conductorClient.startWorkflow(startWorkflowRequest);
    }
}
