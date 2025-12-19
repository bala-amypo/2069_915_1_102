// src/main/java/com/example/demo/repository/AlertScheduleRepository.java
package com.example.demo.repository;

import com.example.demo.entity.AlertSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertScheduleRepository extends JpaRepository<AlertSchedule, Long> {
    List<AlertSchedule> findByWarrantyId(Long warrantyId);
}