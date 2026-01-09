package com.example.sneaker_run.service;

import com.example.sneaker_run.domain.Product;
import com.example.sneaker_run.dto.ProductRequest;
import com.example.sneaker_run.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.sneaker_run.dto.ProductResponse;
import java.util.List;

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

    @Transactional(readOnly = true) // 조회 전용이라 readOnly 걸면 성능 좋아짐
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream() // 1. 모든 상품 가져오기
                .map(product -> new ProductResponse(    // 2. 하나씩 DTO로 변환
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getStockQuantity()
                ))
                .toList(); // 3. 리스트로 묶어서 반환
    }
}