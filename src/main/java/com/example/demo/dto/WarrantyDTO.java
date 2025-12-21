// src/main/java/com/example/demo/dto/WarrantyDTO.java
package com.example.demo.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WarrantyDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;
    private String serialNumber;
}