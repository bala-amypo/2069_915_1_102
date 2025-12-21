package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "warranties")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Warranty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Purchase date is required")
    private LocalDate purchaseDate;

    @Future(message = "Expiry date must be after purchase date")
    private LocalDate expiryDate;

    @NotBlank(message = "Serial number required")
    @Column(unique = true)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("warranties")   // ✅ prevents recursion Warranty ↔ User
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnoreProperties("warranties")   // ✅ prevents recursion Warranty ↔ Product
    private Product product;

    @OneToMany(mappedBy = "warranty")
    @JsonIgnoreProperties("warranty")   // ✅ prevents recursion Warranty ↔ AlertSchedule
    private List<AlertSchedule> schedules;

    @OneToMany(mappedBy = "warranty")
    @JsonIgnoreProperties("warranty")   // ✅ prevents recursion Warranty ↔ AlertLog
    private List<AlertLog> logs;
}