package com.example.sneaker_run.service;

import com.example.sneaker_run.domain.User;
import com.example.sneaker_run.dto.SignupRequest;
import com.example.sneaker_run.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // 생성자 주입 자동화 (Lombok)
public class UserService {

    private final UserRepository userRepository;

    @Transactional // DB 작업할 때 트랜잭션 걸어줌 (필수)
    public Long signup(SignupRequest request) {
        // 생성자로 값 넣기
        User user = new User(request.email(), request.name());

        // DB에 저장 (save 메서드는 JpaRepository가 그냥 줌)
        User savedUser = userRepository.save(user);

        return savedUser.getId();
    }
}