package dev.saintho.mytly.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	info = @Info(
		title = "Mytly API Documentation",
		description = "BASE62 기반 URL 단축 서비스 API 명세서",
		version = "v1.0"
	)
)
@Configuration
public class OpenApiConfig {
	private final String[] paths = {"/api/v1/**"};

	@Bean
	public GroupedOpenApi openApi() {
		return GroupedOpenApi.builder()
			.group("v1")
			.pathsToMatch(paths)
			.build();
	}
}
