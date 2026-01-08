package com.example.sneaker_run.controller;

import com.example.sneaker_run.dto.SignupRequest;
import com.example.sneaker_run.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // POST 요청: 데이터 저장할 때 씀
    @PostMapping("/api/users/signup")
    public String signup(@RequestBody SignupRequest request) {
        Long userId = userService.signup(request);
        return "회원가입 성공! 유저 ID: " + userId;
    }
}