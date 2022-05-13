package com.example.sd_project;

import com.example.sd_project.business.model.Admin;
import com.example.sd_project.business.model.Store;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class SdProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdProjectApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE", "PATCH")
                        .allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }

}
