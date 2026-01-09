package com.example.sneaker_run.repository;

import com.example.sneaker_run.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}