// src/main/java/com/example/demo/entity/Warranty.java
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @NotNull(message = "Purchase date is required")
  private LocalDate purchaseDate;

  @NotNull(message = "Expiry date is required")
  @Future(message = "Expiry date must be after purchase date")
  private LocalDate expiryDate;

  @NotBlank(message = "Serial number required")
  @Column(unique = true)
  private String serialNumber;

  @OneToMany(mappedBy = "warranty", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AlertSchedule> schedules = new ArrayList<>();

  @OneToMany(mappedBy = "warranty", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AlertLog> logs = new ArrayList<>();
}