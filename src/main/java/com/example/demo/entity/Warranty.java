// // Warranty.java
// package com.example.demo.entity;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;

// import java.time.LocalDate;

// @Entity
// @Table(name = "warranties", uniqueConstraints = @UniqueConstraint(columnNames = "serialNumber"))
// public class Warranty {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(optional = false)
//     private User user;

//     @ManyToOne(optional = false)
//     private Product product;

//     @NotNull
//     private LocalDate purchaseDate;

//     @NotNull
//     private LocalDate expiryDate;

//     @NotBlank
//     @Column(unique = true)
//     private String serialNumber;

//     @PrePersist
//     @PreUpdate
//     public void validateDates() {
//         if (expiryDate.isBefore(purchaseDate)) {
//             throw new IllegalArgumentException("Expiry date must be after purchase date");
//         }
//     }

//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public User getUser() { return user; }
//     public void setUser(User user) { this.user = user; }

//     public Product getProduct() { return product; }
//     public void setProduct(Product product) { this.product = product; }

//     public LocalDate getPurchaseDate() { return purchaseDate; }
//     public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }

//     public LocalDate getExpiryDate() { return expiryDate; }
//     public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

//     public String getSerialNumber() { return serialNumber; }
//     public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
// }

// src/main/java/com/example/demo/entity/Warranty.java
package com.example.demo.entity;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Warranty {
    private Long id;
    private User user;
    private Product product;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;
    private String serialNumber;
}