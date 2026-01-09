package com.example.sneaker_run.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders") // 'order'는 SQL 예약어라 에러날 수 있어서 이름 변경
@Getter
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 한 명의 유저는 여러 주문을 할 수 있음
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // 하나의 상품은 여러 주문에 포함될 수 있음
    @JoinColumn(name = "product_id")
    private Product product;

    private Long count; // 주문 수량

    private LocalDateTime orderDate; // 주문 시간

    // 생성자 (주문 생성 시 바로 재고를 까버림)
    public Order(User user, Product product, Long count) {
        this.user = user;
        this.product = product;
        this.count = count;
        this.orderDate = LocalDateTime.now();
    }
}