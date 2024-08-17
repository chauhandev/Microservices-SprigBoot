package com.learning.microservices.product_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI productServiceAPI() {
		return new OpenAPI().info(new Info().title("Product Service API").description("this is a REST api for product service"));
	}
}
