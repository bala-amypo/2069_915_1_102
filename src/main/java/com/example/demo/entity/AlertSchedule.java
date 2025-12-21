// src/main/java/com/example/demo/entity/AlertSchedule.java
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "alert_schedules")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertSchedule {

  // Primary key
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Many-to-one relationship: warranty can have multiple schedules
  @ManyToOne
  @JoinColumn(name = "warranty_id", nullable = false)
  private Warranty warranty;

  // Number of days before expiry when alert should be sent
  @NotNull(message = "daysBeforeExpiry is required")
  @Min(value = 0, message = "daysBeforeExpiry must be >= 0")
  private Integer daysBeforeExpiry;

  // Whether alerts from this schedule are active
  @NotNull(message = "enabled flag is required")
  private Boolean enabled;
}