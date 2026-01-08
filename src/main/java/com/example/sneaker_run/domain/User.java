package com.example.sneaker_run.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity  // "이 클래스는 DB 테이블이 될 거야" 라고 선언
@Getter
@Setter
@NoArgsConstructor // 이거 3개 추가!
public class User {

    @Id // "이게 PK(기본키)야"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // "AUTO_INCREMENT로 해줘"
    private Long id;

    private String name;
    private String email;

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
}