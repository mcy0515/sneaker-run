package com.example.sneaker_run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "드디어 프로젝트 생성 성공! 취업 가자!";
    }
}