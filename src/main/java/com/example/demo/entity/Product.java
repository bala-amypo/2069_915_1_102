package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Model number is required")
    @Column(unique = true)
    private String modelNumber;

    @NotBlank(message = "Category is required")
    private String category;

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties("product")   
    private List<Warranty> warranties;
}