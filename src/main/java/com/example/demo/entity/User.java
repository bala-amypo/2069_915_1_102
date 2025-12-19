// 
// src/main/java/com/example/demo/entity/User.java
package com.example.demo.entity;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
}