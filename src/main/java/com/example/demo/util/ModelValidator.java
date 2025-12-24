package com.example.demo.util;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.Warranty;

import java.time.LocalDate;

public class ModelValidator {

    public static void validateProduct(Product p) {
        if (p.getModelNumber() == null || p.getModelNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Model number required");
        }
        if (p.getCategory() == null || p.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Category required");
        }
    }

    public static void validateUser(User u) {
        if (u.getEmail() == null || u.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email required");
        }
        if (u.getPassword() == null || u.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password required");
        }
    }

    public static void validateWarranty(Warranty w) {
        if (w.getPurchaseDate() == null || w.getExpiryDate() == null) {
            throw new IllegalArgumentException("Purchase and expiry dates required");
        }
        if (!w.getExpiryDate().isAfter(w.getPurchaseDate())) {
            throw new IllegalArgumentException("Expiry date must be after purchase date");
        }
        if (w.getSerialNumber() == null || w.getSerialNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Serial number required");
        }
    }

    public static void validateScheduleDays(Integer daysBeforeExpiry) {
        if (daysBeforeExpiry == null || daysBeforeExpiry < 0) {
            throw new IllegalArgumentException("daysBeforeExpiry must be non-negative");
        }
    }
}