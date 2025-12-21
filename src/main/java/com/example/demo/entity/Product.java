// src/main/java/com/example/demo/entity/Product.java
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

  @NotBlank(message = "Brand is required")
  private String brand;

  @NotBlank(message = "Model number required")
  @Column(unique = true)
  private String modelNumber;

  @NotBlank(message = "Category required")
  private String category;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Warranty> warranties = new ArrayList<>();
}