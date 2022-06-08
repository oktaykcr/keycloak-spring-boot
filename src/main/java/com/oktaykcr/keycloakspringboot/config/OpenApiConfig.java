package com.oktaykcr.keycloakspringboot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Oktay Kocer");
        contact.setUrl("https://github.com/oktaykcr/keycloak-spring-boot");
        contact.setEmail("oktaykocer@outlook.com");

        return new OpenAPI()
                .info(new Info().title("Keycloak Spring Boot API")
                        .description("Keycloak authorization with some extra topics")
                        .version("v0.0.1")
                        .contact(contact));
    }

}
