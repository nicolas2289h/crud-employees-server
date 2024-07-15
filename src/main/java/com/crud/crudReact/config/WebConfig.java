package com.crud.crudReact.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedOrigins("http://127.0.0.1:5173", "http://127.0.0.1:3000", "http://localhost:3000", "http://localhost:5173", "https://nh-employees.vercel.app")
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders("abc")
                .maxAge(36L);
    }
}