package com.example.sneaker_run.dto;

public record ProductResponse(
        Long id,
        String name,
        Long price,
        Long stockQuantity
) {}