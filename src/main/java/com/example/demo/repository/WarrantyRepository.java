// // WarrantyRepository.java
// package com.example.demo.repository;

// import com.example.demo.entity.Warranty;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
// }

// src/main/java/com/example/demo/repository/WarrantyRepository.java
package com.example.demo.repository;

import com.example.demo.entity.Warranty;
import java.time.LocalDate;
import java.util.*;

public interface WarrantyRepository {
    Warranty save(Warranty warranty);
    Optional<Warranty> findById(Long id);
    List<Warranty> findByUserId(Long userId);
    boolean existsBySerialNumber(String serialNumber);
    List<Warranty> findWarrantiesExpiringBetween(LocalDate from, LocalDate to);
}