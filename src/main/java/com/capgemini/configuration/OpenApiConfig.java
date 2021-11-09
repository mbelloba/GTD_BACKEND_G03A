package com.capgemini.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Api configuration class
 * @author GTD-G03A
 *
 */
@Configuration
public class OpenApiConfig {

	/**
	 * Configuration API, set title and description
	 * @return OpenAPI
	 */
	@Bean
	public OpenAPI customConfiguration() {
		return new OpenAPI().components(new Components()).info(new Info().title("API DOCS GTD_BACKEND_G03A").description("Sample REST API documentation"));
	}
}
