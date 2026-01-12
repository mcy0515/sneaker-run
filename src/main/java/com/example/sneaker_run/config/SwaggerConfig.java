package com.example.sneaker_run.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // "이건 설정 파일이야"라고 스프링에게 알려줌
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SneakerRun API 명세서")
                        .description("선착순 한정판 스니커즈 판매 서비스 API입니다.")
                        .version("1.0.0"));
    }
}