package com.groupshow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/auth/refresh-access")
                        .allowedOrigins("http://127.0.0.1:5173/")
                        .allowedHeaders("Authorization", "X-Refresh-Token") // headers allowed in request
                        .exposedHeaders("Authorization", "X-Refresh-Token"); // headers allowed in response
//                        .allowCredentials(true);
            }
        };
    }
}
