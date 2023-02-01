package com.caito.usuarios.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "Bearer"
)
public class SwaggerConfig {

    final String securityScemeName = "bearerAuth";

    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .group("User")
                .packagesToScan("com.caito")
                .build();
    }

    @Bean
    public OpenAPI springShopOpemapi(){
        return new OpenAPI()
                /*.addSecurityItem(new SecurityRequirement().addList(securityScemeName))
                .components(new Components())*/
                .info(new Info().title("Gestion Usuarios Api")
                        .description("Gestor para el manejo de Usuarios")
                        .version("v1.0"));

    }
}
