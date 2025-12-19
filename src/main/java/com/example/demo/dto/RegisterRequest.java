// src/main/java/com/example/demo/dto/RegisterRequest.java
package com.example.demo.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}