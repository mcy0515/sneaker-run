package com.example.sneaker_run.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // 상품명 (예: 나이키 조던 1)
    private Long price;         // 가격
    private Long stockQuantity; // 재고 수량
    private String description; // 상품 설명

    // 상품 등록할 때 쓸 생성자
    public Product(String name, Long price, Long stockQuantity, String description) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
    }

    // 재고 감소 로직 (비즈니스 로직)
    public void removeStock(Long quantity) {
        long restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new IllegalArgumentException("재고가 부족합니다."); // 예외 발생!
        }
        this.stockQuantity = restStock;
    }
}