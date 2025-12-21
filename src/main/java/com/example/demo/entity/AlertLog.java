// src/main/java/com/example/demo/entity/AlertLog.java
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert_logs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertLog {

  // Primary key
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Many-to-one relationship: each warranty can have multiple logs
  @ManyToOne
  @JoinColumn(name = "warranty_id", nullable = false)
  private Warranty warranty;

  // Auto-generated timestamp when alert is sent/simulated
  private LocalDateTime sentAt;

  // Message/body of the alert
  @NotBlank(message = "Message is required")
  private String message;

  // Auto-set sentAt before persisting
  @PrePersist
  public void prePersist() {
    this.sentAt = LocalDateTime.now();
  }
}