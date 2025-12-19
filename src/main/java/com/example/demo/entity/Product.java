// // Product.java
// package com.example.demo.entity;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.NotBlank;

// @Entity
// @Table(name = "products")
// public class Product {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotBlank
//     private String name;

//     private String brand;

//     @NotBlank(message = "Model number required")
//     private String modelNumber;

//     @NotBlank(message = "Category required")
//     private String category;

//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }

//     public String getBrand() { return brand; }
//     public void setBrand(String brand) { this.brand = brand; }

//     public String getModelNumber() { return modelNumber; }
//     public void setModelNumber(String modelNumber) { this.modelNumber = modelNumber; }

//     public String getCategory() { return category; }
//     public void setCategory(String category) { this.category = category; }
// }

// src/main/java/com/example/demo/entity/Product.java
package com.example.demo.entity;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    private Long id;
    private String name;
    private String brand;
    private String modelNumber;
    private String category;
}