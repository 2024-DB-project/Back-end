package com.example.employee_api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() throws IOException {
        OpenAPI openAPI = new OpenAPI().info(new Info().title("Your API").version("1.0"));

        // JSON 파일을 불러와 병합
        String customSwaggerJson = Files.readString(new ClassPathResource("static/openapi.json").getFile().toPath());
        OpenAPIV3Parser parser = new OpenAPIV3Parser();
        SwaggerParseResult result = parser.readContents(customSwaggerJson);

        // Paths 객체를 초기화
        if (openAPI.getPaths() == null) {
            openAPI.setPaths(new io.swagger.v3.oas.models.Paths());
        }

        // Components의 Schemas를 초기화
        if (openAPI.getComponents() == null) {
            openAPI.setComponents(new io.swagger.v3.oas.models.Components());
        }

        // 병합
        if (result.getOpenAPI() != null) {
            openAPI.getPaths().putAll(result.getOpenAPI().getPaths());
            if (result.getOpenAPI().getComponents() != null &&
                    result.getOpenAPI().getComponents().getSchemas() != null) {
                openAPI.getComponents().setSchemas(
                        result.getOpenAPI().getComponents().getSchemas());
            }
        }
        return openAPI;
    }
}
