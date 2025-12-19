// src/main/java/com/example/demo/entity/Warranty.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Warranty {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;
    private String serialNumber;

    @ManyToOne private User user;
    @ManyToOne private Product product;
}