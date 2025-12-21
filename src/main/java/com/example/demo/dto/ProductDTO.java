// src/main/java/com/example/demo/dto/ProductDTO.java
package com.example.demo.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductDTO {
    private Long id;
    private String brand;
    private String modelNumber;
    private String category;
}