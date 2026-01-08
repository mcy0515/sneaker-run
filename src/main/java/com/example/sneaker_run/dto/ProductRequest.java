package com.example.sneaker_run.dto;

public record ProductRequest(
        String name,
        Long price,
        Long stockQuantity,
        String description
) {}