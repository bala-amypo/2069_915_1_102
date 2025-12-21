// src/main/java/com/example/demo/entity/Warranty.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "warranties")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Warranty {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Owning side: many warranties belong to one user
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  // Owning side: many warranties belong to one product
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  private LocalDate purchaseDate;
  private LocalDate expiryDate;
  private String serialNumber; // Service should enforce uniqueness

  // One warranty can have many schedules (inverse side)
  @OneToMany(mappedBy = "warranty", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AlertSchedule> schedules = new ArrayList<>();

  // One warranty can have many logs (inverse side)
  @OneToMany(mappedBy = "warranty", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AlertLog> logs = new ArrayList<>();
}