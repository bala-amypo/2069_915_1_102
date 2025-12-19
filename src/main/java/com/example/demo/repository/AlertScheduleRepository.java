// // AlertScheduleRepository.java
// package com.example.demo.repository;

// import com.example.demo.entity.AlertSchedule;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface AlertScheduleRepository extends JpaRepository<AlertSchedule, Long> {
// }

// src/main/java/com/example/demo/repository/AlertScheduleRepository.java
package com.example.demo.repository;

import com.example.demo.entity.AlertSchedule;
import java.util.*;

public interface AlertScheduleRepository {
    AlertSchedule save(AlertSchedule schedule);
    List<AlertSchedule> findByWarrantyId(Long warrantyId);
}