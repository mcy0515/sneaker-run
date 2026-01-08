package com.example.sneaker_run.repository;

import com.example.sneaker_run.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}