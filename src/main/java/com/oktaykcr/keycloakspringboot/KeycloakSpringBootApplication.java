package com.oktaykcr.keycloakspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KeycloakSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeycloakSpringBootApplication.class, args);
    }

}
