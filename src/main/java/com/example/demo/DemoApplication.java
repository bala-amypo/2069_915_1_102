package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  // <-- missing import

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        // quick password check
        String raw = "string123";
        String hash = "$2a$10$6kSgxgNP0fu4WIOXUP1kUelcj/Q5lvhSjx5Ye0V.eQiDXGCf62Bka";

        boolean matches = new BCryptPasswordEncoder().matches(raw, hash);
        System.out.println("Matches: " + matches);
    }
}