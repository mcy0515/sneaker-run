package com.example.sneaker_run.controller;

import com.example.sneaker_run.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // "나는 모든 컨트롤러의 에러를 감시해"
public class GlobalExceptionHandler {

    // IllegalArgumentException (우리가 자주 쓴 에러)가 터지면 얘가 잡습니다.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
        // 400 Bad Request 상태코드와 함께 에러 메시지 반환
        return ResponseEntity.status(400)
                .body(new ErrorResponse("400", e.getMessage()));
    }
}