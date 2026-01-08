package com.example.sneaker_run.dto;

// record: Java 14+에 나온 데이터 전송 전용 객체 (Getter, 생성자 자동 생성됨)
public record SignupRequest(
        String email,
        String name
) {}