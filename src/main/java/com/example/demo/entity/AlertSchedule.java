// src/main/java/com/example/demo/entity/AlertSchedule.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertSchedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer daysBeforeExpiry;
    private Boolean enabled;
    @ManyToOne private Warranty warranty;
}