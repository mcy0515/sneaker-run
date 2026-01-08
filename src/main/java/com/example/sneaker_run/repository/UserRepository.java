package com.example.sneaker_run.repository;

import com.example.sneaker_run.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// <User, Long> : User 엔티티를 다루고, PK는 Long 타입이다.
public interface UserRepository extends JpaRepository<User, Long> {
}