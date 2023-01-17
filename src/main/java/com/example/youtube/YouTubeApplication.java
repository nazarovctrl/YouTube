package com.example.youtube;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Youtube API",version = "1.0",description = "You tube"))
public class YouTubeApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouTubeApplication.class, args);
    }

}
