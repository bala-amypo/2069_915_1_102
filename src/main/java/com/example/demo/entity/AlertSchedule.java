// // AlertSchedule.java
// package com.example.demo.entity;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.Min;

// @Entity
// @Table(name = "alert_schedules")
// public class AlertSchedule {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(optional = false)
//     private Warranty warranty;

//     @Min(value = 0, message = "daysBeforeExpiry must be >= 0")
//     private Integer daysBeforeExpiry;

//     private Boolean enabled = true;

//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public Warranty getWarranty() { return warranty; }
//     public void setWarranty(Warranty warranty) { this.warranty = warranty; }

//     public Integer getDaysBeforeExpiry() { return daysBeforeExpiry; }
//     public void setDaysBeforeExpiry(Integer daysBeforeExpiry) { this.daysBeforeExpiry = daysBeforeExpiry; }

//     public Boolean getEnabled() { return enabled; }
//     public void setEnabled(Boolean enabled) { this.enabled = enabled; }
// }

// src/main/java/com/example/demo/entity/AlertSchedule.java
package com.example.demo.entity;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertSchedule {
    private Long id;
    private Long warrantyId;
    private Integer daysBeforeExpiry;
    private Boolean enabled;
}