package com.example.sneaker_run.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity  // "이 클래스는 DB 테이블이 될 거야" 라고 선언
public class User {

    @Id // "이게 PK(기본키)야"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // "AUTO_INCREMENT로 해줘"
    private Long id;

    private String name;
    private String email;
}