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

  private LocalDate purchaseDate;
  private LocalDate expiryDate;
  private String serialNumber; // Service should enforce uniqueness

  // One warranty can have many schedules via join table
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(
      name = "warranty_schedules",
      joinColumns = @JoinColumn(name = "warranty_id"),
      inverseJoinColumns = @JoinColumn(name = "schedule_id")
  )
  private List<AlertSchedule> schedules = new ArrayList<>();

  // One warranty can have many logs via join table
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(
      name = "warranty_logs",
      joinColumns = @JoinColumn(name = "warranty_id"),
      inverseJoinColumns = @JoinColumn(name = "log_id")
  )
  private List<AlertLog> logs = new ArrayList<>();
}