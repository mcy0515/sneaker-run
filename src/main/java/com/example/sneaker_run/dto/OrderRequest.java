package com.example.sneaker_run.dto;

public record OrderRequest(
        Long userId,
        Long productId,
        Long quantity
) {}