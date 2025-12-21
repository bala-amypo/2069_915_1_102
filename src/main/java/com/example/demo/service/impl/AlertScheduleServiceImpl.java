package com.example.demo.service.impl;

import com.example.demo.entity.AlertSchedule;
import com.example.demo.entity.Warranty;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AlertScheduleRepository;
import com.example.demo.repository.WarrantyRepository;
import com.example.demo.service.AlertScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertScheduleServiceImpl implements AlertScheduleService {
    private final AlertScheduleRepository scheduleRepository;
    private final WarrantyRepository warrantyRepository;

    public AlertScheduleServiceImpl(AlertScheduleRepository s, WarrantyRepository w) {
        this.scheduleRepository = s; this.warrantyRepository = w;
    }

    @Override
    public AlertSchedule createSchedule(Long warrantyId, AlertSchedule schedule) {
        Warranty warranty = warrantyRepository.findById(warrantyId)
            .orElseThrow(() -> new ResourceNotFoundException("Given data not found"));

        schedule.setWarranty(warranty);
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<AlertSchedule> getSchedules(Long warrantyId) {
        warrantyRepository.findById(warrantyId)
            .orElseThrow(() -> new ResourceNotFoundException("Given data not found"));

        List<AlertSchedule> schedules = scheduleRepository.findByWarrantyId(warrantyId);
        if (schedules.isEmpty()) {
            throw new ResourceNotFoundException("No schedules found for the given warranty ID");
        }
        return schedules;
    }
}