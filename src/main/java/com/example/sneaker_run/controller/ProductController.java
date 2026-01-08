package com.example.sneaker_run.controller;

import com.example.sneaker_run.dto.ProductRequest;
import com.example.sneaker_run.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/products")
    public String createProduct(@RequestBody ProductRequest request) {
        Long productId = productService.createProduct(request);
        return "상품 등록 성공! 상품 ID: " + productId;
    }
}