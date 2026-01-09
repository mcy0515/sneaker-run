package com.example.sneaker_run.dto;

// 에러가 나면 이 모양으로 데이터를 줄 겁니다.
public record ErrorResponse(
        String code,
        String message
) {}