// src/main/java/com/example/demo/entity/AlertLog.java
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "alert_logs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Alert timestamp is required")
  private LocalDate alertTimestamp;

  @NotBlank(message = "Message is required")
  private String message;

  @ManyToOne
  @JoinColumn(name = "warranty_id", nullable = false)
  private Warranty warranty;
}