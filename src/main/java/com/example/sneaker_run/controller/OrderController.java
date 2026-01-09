package com.example.sneaker_run.controller;

import com.example.sneaker_run.dto.OrderRequest;
import com.example.sneaker_run.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/orders")
    public String createOrder(@RequestBody OrderRequest request) {
        Long orderId = orderService.order(request);
        return "주문 성공! 주문 ID: " + orderId;
    }
}