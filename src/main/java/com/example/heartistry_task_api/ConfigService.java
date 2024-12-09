package com.example.heartistry_task_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
    @Value("${my.app.jwt-secret}")
    private String jwtSecret;

    public String getJwtSecret() {
        return jwtSecret;
    }
}