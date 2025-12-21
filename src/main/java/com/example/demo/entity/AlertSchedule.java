// src/main/java/com/example/demo/entity/AlertSchedule.java
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "alert_schedules")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertSchedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Alert date is required")
  private LocalDate alertDate;

  @ManyToOne
  @JoinColumn(name = "warranty_id", nullable = false)
  private Warranty warranty;
}