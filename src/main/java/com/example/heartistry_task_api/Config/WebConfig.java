package com.example.heartistry_task_api.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints
                .allowedOrigins("*") // Allow requests from any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // Allow specific HTTP methods
                .allowedHeaders("*") // Allow any headers
                .allowCredentials(false) // Disable sending cookies and credentials
                .maxAge(3600); // Cache preflight requests for 1 hour
    }
}
