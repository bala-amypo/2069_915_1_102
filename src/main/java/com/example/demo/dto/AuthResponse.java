// src/main/java/com/example/demo/dto/AuthResponse.java
package com.example.demo.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuthResponse {
    private Long userId;
    private String email;
    private String role;
    // token field optional; omit if JWT excluded
}