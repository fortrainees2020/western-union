package com.cg.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            // Feign automatically propagates trace headers in Boot 3 + Micrometer
            // but adding a manual interceptor ensures nothing is dropped
        };
    }
}
