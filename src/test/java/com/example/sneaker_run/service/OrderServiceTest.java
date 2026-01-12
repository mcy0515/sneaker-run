// ▼▼▼ 여기가 중요! 방금 만든 폴더 경로랑 똑같아야 함 ▼▼▼
package com.example.sneaker_run.service;

import com.example.sneaker_run.domain.Product;
import com.example.sneaker_run.domain.User;
import com.example.sneaker_run.dto.OrderRequest;
import com.example.sneaker_run.repository.OrderRepository;
import com.example.sneaker_run.repository.ProductRepository;
import com.example.sneaker_run.repository.RedisStockRepository;
import com.example.sneaker_run.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired ProductRepository productRepository;
    @Autowired UserRepository userRepository;
    @Autowired OrderRepository orderRepository;

    @Autowired
    RedisStockRepository redisStockRepository;

    @Test
    @DisplayName("동시에 100명이 주문하면 재고는 0이 되어야 한다.")
    void concurrencyTest() throws InterruptedException {
        // ... (내용은 아까와 동일)
        // 1. 테스트 데이터 준비
        User user = userRepository.save(new User("test@test.com", "테스터"));
        Product product = productRepository.save(new Product("나이키 한정판", 100000L, 100L, "설명"));

        redisStockRepository.setStock(product.getId(), 100L);

        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    OrderRequest request = new OrderRequest(user.getId(), product.getId(), 1L);
                    orderService.order(request);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Long stock = redisStockRepository.getStock(product.getId());
        assertEquals(0, stock);
    }
}