package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert_logs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime sentAt;
  private String message;

  @PrePersist
  public void prePersist() {
    if (sentAt == null) {
      sentAt = LocalDateTime.now();
    }
  }
}