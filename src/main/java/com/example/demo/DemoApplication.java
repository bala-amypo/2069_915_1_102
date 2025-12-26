package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  // <-- missing import

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        String raw = "string123456";
        String hash = "$2a$10$7GSNH5QuptSeEUxQus6o1.eR/ICX2ABKSDRafX1jdlkkgcSdJ4lCG";

        boolean matches = new BCryptPasswordEncoder().matches(raw, hash);
        System.out.println("Matches: " + matches);
    }
}