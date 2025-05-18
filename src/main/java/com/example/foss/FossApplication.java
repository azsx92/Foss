package com.example.foss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FossApplication {

    public static void main(String[] args) {
        SpringApplication.run(FossApplication.class, args);
    }

}
