// // AlertLog.java
// package com.example.demo.entity;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.NotBlank;

// import java.time.LocalDateTime;

// @Entity
// @Table(name = "alert_logs")
// public class AlertLog {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(optional = false)
//     private Warranty warranty;

//     private LocalDateTime sentAt;

//     @NotBlank
//     private String message;

//     @PrePersist
//     public void setTimestamp() {
//         this.sentAt = LocalDateTime.now();
//     }

//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public Warranty getWarranty() { return warranty; }
//     public void setWarranty(Warranty warranty) { this.warranty = warranty; }

//     public LocalDateTime getSentAt() { return sentAt; }
//     public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }

//     public String getMessage() { return message; }
//     public void setMessage(String message) { this.message = message; }
// }

// src/main/java/com/example/demo/entity/AlertLog.java
package com.example.demo.entity;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertLog {
    private Long id;
    private Long warrantyId;
    private String message;
    private LocalDateTime sentAt;

    // Simulate JPA lifecycle timestamp generation
    public void prePersist() {
        if (sentAt == null) {
            sentAt = LocalDateTime.now();
        }
    }
}