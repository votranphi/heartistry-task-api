package com.example.heartistry_task_api.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Heartistry Task API Documentation",
        version = "1.0",
        description = "RESTful API for Heartistry (English related tasks management)"
    )
)
public class OpenAPIConfig {
}
