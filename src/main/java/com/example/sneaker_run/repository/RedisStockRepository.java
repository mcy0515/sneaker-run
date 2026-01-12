package com.example.sneaker_run.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisStockRepository {

    private final RedisTemplate<String, String> redisTemplate;

    // 상품 재고를 Redis에 세팅 (초기화용)
    public void setStock(Long productId, Long stock) {
        redisTemplate.opsForValue().set(String.valueOf(productId), String.valueOf(stock));
    }

    // 재고 감소 (핵심: Redis는 이 명령어가 원자적(Atomic)이라 동시성 문제 없음)
    public Long decrementStock(Long productId, Long quantity) {
        return redisTemplate.opsForValue().decrement(String.valueOf(productId), quantity);
    }

    // 재고 조회
    public Long getStock(Long productId) {
        String stock = redisTemplate.opsForValue().get(String.valueOf(productId));
        return stock == null ? 0L : Long.parseLong(stock);
    }
}