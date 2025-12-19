// src/main/java/com/example/demo/dto/AuthRequest.java
package com.example.demo.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuthRequest {
    private String email;
    private String password;
}