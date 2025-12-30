package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String modelNumber;
    private String category;

    // ✅ REQUIRED BY TEST CASE (priority 11, 25)
    public Product(Long id, String name, String brand, String modelNumber, String category) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.modelNumber = modelNumber;
        this.category = category;
    }
}
