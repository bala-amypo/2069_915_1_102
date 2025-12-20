// src/main/java/com/example/demo/controller/HealthController.java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping
    public String health() { return "OK"; }
}