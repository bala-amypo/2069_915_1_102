package com.example.demo.controller;

import com.example.demo.entity.AlertSchedule;
import com.example.demo.service.AlertScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Alert Schedules", description = "Manage alert schedules for warranties")
@RestController
@RequestMapping("/schedules")
public class AlertScheduleController {

    private final AlertScheduleService service;

    public AlertScheduleController(AlertScheduleService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new alert schedule for a warranty")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/{warrantyId}")
    public AlertSchedule createSchedule(@PathVariable Long warrantyId, @RequestBody AlertSchedule schedule) {
        return service.createSchedule(warrantyId, schedule);
    }

    @Operation(summary = "Get all alert schedules for a warranty")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{warrantyId}")
    public List<AlertSchedule> getSchedules(@PathVariable Long warrantyId) {
        return service.getSchedules(warrantyId);
    }
}