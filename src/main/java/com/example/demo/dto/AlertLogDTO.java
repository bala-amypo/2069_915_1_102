// src/main/java/com/example/demo/dto/AlertLogDTO.java
package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertLogDTO {
    private Long id;
    private Long warrantyId;
    private LocalDateTime sentAt;
    private String message;
}