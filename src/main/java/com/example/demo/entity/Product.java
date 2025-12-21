// src/main/java/com/example/demo/entity/Product.java
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

  @NotBlank(message = "Model number required")
  @Column(nullable = false)
  private String modelNumber;  // must not be null/blank

  @NotBlank(message = "Category required")
  @Column(nullable = false)
  private String category;     // must not be null/blank

  // One product may appear in many warranties (inverse side)
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Warranty> warranties = new ArrayList<>();
}