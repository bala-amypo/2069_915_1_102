package com.example.demo.service.impl;

import com.example.demo.entity.AlertLog;
import com.example.demo.entity.Warranty;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AlertLogRepository;
import com.example.demo.repository.WarrantyRepository;
import com.example.demo.service.AlertLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertLogServiceImpl implements AlertLogService {
    private final AlertLogRepository logRepository;
    private final WarrantyRepository warrantyRepository;

    public AlertLogServiceImpl(AlertLogRepository l, WarrantyRepository w) {
        this.logRepository = l; this.warrantyRepository = w;
    }

    @Override
    public AlertLog addLog(Long warrantyId, String message) {
        Warranty warranty = warrantyRepository.findById(warrantyId)
            .orElseThrow(() -> new ResourceNotFoundException("Given data not found"));

        AlertLog log = AlertLog.builder()
            .warranty(warranty)
            .message(message)
            .build();

        return logRepository.save(log);
    }

    @Override
    public List<AlertLog> getLogs(Long warrantyId) {
        warrantyRepository.findById(warrantyId)
            .orElseThrow(() -> new ResourceNotFoundException("Given data not found"));

        List<AlertLog> logs = logRepository.findByWarrantyId(warrantyId);
        if (logs.isEmpty()) {
            throw new ResourceNotFoundException("No logs found for the given warranty ID");
        }
        return logs;
    }
}