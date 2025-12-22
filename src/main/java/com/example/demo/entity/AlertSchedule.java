package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "alert_schedules")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "daysBeforeExpiry is required")
    @Min(value = 0, message = "daysBeforeExpiry must be >= 0")
    private Integer daysBeforeExpiry;

    @NotNull(message = "enabled flag is required")
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "warranty_id", nullable = false)
    @JsonIgnoreProperties({"schedules", "logs"})  
    private Warranty warranty;
}