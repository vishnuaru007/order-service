package com.aru.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aru.orderservice.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}