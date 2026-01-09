package com.example.sneaker_run.service;

import com.example.sneaker_run.domain.Order;
import com.example.sneaker_run.domain.Product;
import com.example.sneaker_run.domain.User;
import com.example.sneaker_run.dto.OrderRequest;
import com.example.sneaker_run.repository.OrderRepository;
import com.example.sneaker_run.repository.ProductRepository;
import com.example.sneaker_run.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional // 트랜잭션 필수! (도중에 실패하면 재고 줄어든 거 롤백해야 함)
    public Long order(OrderRequest request) {
        // 1. 유저 조회
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));

        // 2. 상품 조회
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new IllegalArgumentException("상품이 없습니다."));

        // 3. 재고 감소 (여기서 수량 부족하면 에러 터짐)
        product.removeStock(request.quantity());

        // 4. 주문 생성 및 저장
        Order order = new Order(user, product, request.quantity());
        orderRepository.save(order);

        return order.getId();
    }
}