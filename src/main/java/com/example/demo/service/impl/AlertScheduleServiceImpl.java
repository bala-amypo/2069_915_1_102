// AlertScheduleServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.AlertSchedule;
import com.example.demo.entity.Warranty;
import com.example.demo.repository.AlertScheduleRepository;
import com.example.demo.repository.WarrantyRepository;
import com.example.demo.service.AlertScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertScheduleServiceImpl implements AlertScheduleService {

    @Autowired private AlertScheduleRepository alertScheduleRepository;
    @Autowired private WarrantyRepository warrantyRepository;

    @Override
    public AlertSchedule createSchedule(Long warrantyId, AlertSchedule schedule) {
        Warranty warranty = warrantyRepository.findById(warrantyId)
                .orElseThrow(() -> new RuntimeException("Warranty not found"));

        schedule.setWarranty(warranty);

        if (schedule.getDaysBeforeExpiry() < 0) {
            throw new RuntimeException("daysBeforeExpiry must be >= 0");
        }

        return alertScheduleRepository.save(schedule);
    }

    @Override
    public List<AlertSchedule> getSchedules(Long warrantyId) {
        Warranty warranty = warrantyRepository.findById(warrantyId)
                .orElseThrow(() -> new RuntimeException("Warranty not found"));

        return alertScheduleRepository.findAll()
                .stream()
                .filter(s -> s.getWarranty().equals(warranty))
                .toList();
    }
}