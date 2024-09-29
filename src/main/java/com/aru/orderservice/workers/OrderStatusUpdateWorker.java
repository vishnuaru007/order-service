package com.aru.orderservice.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aru.orderservice.repositories.OrderRepository;
import com.aru.orderservice.models.Order;

@Service
public class OrderStatusUpdateWorker implements Worker {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String getTaskDefName() {
        return "update_order_status";
    }

    @Override
    public TaskResult execute(Task task) {
        Long orderId = (Long) task.getInputData().get("orderId");
        String status = (String) task.getInputData().get("status");
        Order order = orderRepository.findById(orderId).orElse(null);

        TaskResult result = new TaskResult(task);

        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
            result.setStatus(TaskResult.Status.COMPLETED);
            result.getOutputData().put("orderStatus", status);
        } else {
            result.setStatus(TaskResult.Status.FAILED);
            result.getOutputData().put("error", "Order not found");
        }

        return result;
    }

}