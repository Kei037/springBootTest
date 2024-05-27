package com.example.springboottest.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Springdoc OpenAPI 설정
 * http://localhost:8080/swagger-ui/index.html 에서 확인가능
 */
@Configuration
public class SpringdocOpenapiConfig {
    // 경로에 /api가 포함된 컨트롤러의 경우 REST API로 인식
    @Bean
    public GroupedOpenApi restApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/api/**")
                .group("REST API")
                .build();
    }

    // 경로에 /api가 포함되지 않은 컨트롤러의 경우 Common API로 인식
    @Bean
    public GroupedOpenApi commonApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/**/*")
                .group("Common API")
                .build();
    }
}
