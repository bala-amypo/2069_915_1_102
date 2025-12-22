package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@Entity
@Table(name = "alert_logs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime sentAt;

    @NotBlank(message = "Message is required")
    private String message;

    @ManyToOne
    @JoinColumn(name = "warranty_id", nullable = false)
    @JsonIgnoreProperties({"schedules", "logs"})   
    private Warranty warranty;

    @PrePersist
    public void prePersist() {
        this.sentAt = LocalDateTime.now();
    }
}