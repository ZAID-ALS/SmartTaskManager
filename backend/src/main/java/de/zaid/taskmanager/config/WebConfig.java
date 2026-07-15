package de.zaid.taskmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// Erlaubt dem lokalen Frontend den Zugriff auf die REST-API.
public class WebConfig implements WebMvcConfigurer {
    @Override
    // Legt die erlaubte Frontend-Adresse und die HTTP-Methoden fest.
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS");
    }
}
