// src/main/java/com/example/demo/repository/WarrantyRepository.java
package com.example.demo.repository;

import com.example.demo.entity.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    boolean existsBySerialNumber(String serialNumber);
    List<Warranty> findByUserId(Long userId);

    // Derived query on the actual field
    List<Warranty> findByExpiryDateBetween(LocalDate from, LocalDate to);

    // Adapter to satisfy test method name
    default List<Warranty> findWarrantiesExpiringBetween(LocalDate from, LocalDate to) {
        return findByExpiryDateBetween(from, to);
    }
}