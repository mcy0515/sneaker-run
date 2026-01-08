package com.example.sneaker_run.service;

import com.example.sneaker_run.domain.Product;
import com.example.sneaker_run.dto.ProductRequest;
import com.example.sneaker_run.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long createProduct(ProductRequest request) {
        // DTO -> Entity 변환
        Product product = new Product(
                request.name(),
                request.price(),
                request.stockQuantity(),
                request.description()
        );

        // DB 저장
        Product savedProduct = productRepository.save(product);

        return savedProduct.getId();
    }
}