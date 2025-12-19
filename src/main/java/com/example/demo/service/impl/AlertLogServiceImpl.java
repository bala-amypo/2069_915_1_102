// src/main/java/com/example/demo/service/impl/AlertLogServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.AlertLog;
import com.example.demo.entity.Warranty;
import com.example.demo.repository.AlertLogRepository;
import com.example.demo.repository.WarrantyRepository;
import com.example.demo.service.AlertLogService;

import java.util.List;

public class AlertLogServiceImpl implements AlertLogService {

    private final AlertLogRepository logRepository;
    private final WarrantyRepository warrantyRepository;

    public AlertLogServiceImpl(AlertLogRepository logRepository, WarrantyRepository warrantyRepository) {
        this.logRepository = logRepository;
        this.warrantyRepository = warrantyRepository;
    }

    @Override
    public AlertLog addLog(Long warrantyId, String message) {
        Warranty warranty = warrantyRepository.findById(warrantyId)
                .orElseThrow(() -> new RuntimeException("Warranty not found"));

        // Use builder with warranty object, not warrantyId
        AlertLog log = AlertLog.builder()
                .warranty(warranty)
                .message(message)
                .build();

        log.prePersist(); // set sentAt timestamp
        return logRepository.save(log);
    }

    @Override
    public List<AlertLog> getLogs(Long warrantyId) {
        warrantyRepository.findById(warrantyId)
                .orElseThrow(() -> new RuntimeException("Warranty not found"));
        return logRepository.findByWarrantyId(warrantyId);
    }
}