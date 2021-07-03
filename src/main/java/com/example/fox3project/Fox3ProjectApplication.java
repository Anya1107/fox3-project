package com.example.fox3project;

import com.example.fox3project.properties.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class Fox3ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Fox3ProjectApplication.class, args);
    }

}
