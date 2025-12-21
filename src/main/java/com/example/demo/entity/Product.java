// src/main/java/com/example/demo/entity/Product.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String brand;
  private String modelNumber;  // Service should validate not null/blank
  private String category;     // Service should validate not null/blank

  // One product may appear in many warranties (inverse side)
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Warranty> warranties = new ArrayList<>();
}