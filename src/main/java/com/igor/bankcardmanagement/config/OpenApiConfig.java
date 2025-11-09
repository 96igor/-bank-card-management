package com.igor.bankcardmanagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Bank Card Management API", version = "1.0", description = "API для управления банковскими картами")
)
public class OpenApiConfig {
}