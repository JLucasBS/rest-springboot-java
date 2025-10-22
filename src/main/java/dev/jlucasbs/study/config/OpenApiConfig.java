package dev.jlucasbs.study.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's")
                        .version("1.0")
                        .description("REST API's for study")
                        .termsOfService("https://github.com/JLucasBS")
                        .license(new License()
                                .name("License")
                                .url("https://github.com/JLucasBS"))
                );
    }
}
