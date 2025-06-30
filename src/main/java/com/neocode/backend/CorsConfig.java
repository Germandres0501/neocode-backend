package com.neocode.backend; // Asegúrate de que coincide con tu estructura

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Solo rutas que empiezan con /api/
                        .allowedOrigins("http://localhost:3000") // Tu frontend React
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                        .allowedHeaders("*"); // Todos los headers permitidos
            }
        };
    }
}
