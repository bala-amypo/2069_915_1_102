// src/main/java/com/example/demo/dto/AlertScheduleDTO.java
package com.example.demo.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertScheduleDTO {
    private Long id;
    private Long warrantyId;
    private Integer daysBeforeExpiry;
    private Boolean enabled;
}