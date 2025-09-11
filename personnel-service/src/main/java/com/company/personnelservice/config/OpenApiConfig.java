package com.company.personnelservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Personnel Service API",
                version = "v1",
                description = "Internship project - Employees CRUD, search, pagination"
        )
)
@Configuration
public class OpenApiConfig {
}
