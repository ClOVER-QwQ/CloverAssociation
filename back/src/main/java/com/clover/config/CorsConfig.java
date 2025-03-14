package com.clover.config;

import io.micrometer.common.lang.NonNullApi;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Setter
@Getter
@NonNullApi
@Configuration
@ConfigurationProperties(prefix = "cors")
public class CorsConfig {

    // Getters and Setters
    private String allowedOrigins;       // 对应 cors.allowed-origins
    private String allowedMethods;      // 对应 cors.allowed-methods
    private String allowedHeaders;      // 对应 cors.allowed-headers
    private boolean allowCredentials;   // 对应 cors.allow-credentials

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins.split(","))
                        .allowedMethods(allowedMethods.split(","))
                        .allowedHeaders(allowedHeaders)
                        .allowCredentials(allowCredentials);
            }
        };
    }
}