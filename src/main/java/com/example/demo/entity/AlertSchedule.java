package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class AlertSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int daysBeforeExpiry;

    @ManyToOne
    private Warranty warranty;

    public Long getId() {
        return id;
    }

    public int getDaysBeforeExpiry() {
        return daysBeforeExpiry;
    }

    public void setDaysBeforeExpiry(int daysBeforeExpiry) {
        this.daysBeforeExpiry = daysBeforeExpiry;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }
}
