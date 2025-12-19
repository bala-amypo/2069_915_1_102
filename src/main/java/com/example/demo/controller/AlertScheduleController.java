package com.example.demo.controller;

import com.example.demo.entity.AlertSchedule;
import com.example.demo.service.AlertScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class AlertScheduleController {

    @Autowired
    private AlertScheduleService alertScheduleService;

    // POST /schedules/{warrantyId} — create schedule
    @PostMapping("/{warrantyId}")
    public AlertSchedule createSchedule(@PathVariable Long warrantyId,
                                        @RequestBody AlertSchedule schedule) {
        return alertScheduleService.createSchedule(warrantyId, schedule);
    }

    // GET /schedules/{warrantyId} — list schedules for warranty
    @GetMapping("/{warrantyId}")
    public List<AlertSchedule> getSchedules(@PathVariable Long warrantyId) {
        return alertScheduleService.getSchedules(warrantyId);
    }
}
    @Autowired
    private AlertScheduleService alertScheduleService;

    @PostMapping("/{warrantyId}")
    public AlertSchedule createSchedule(@PathVariable Long warrantyId, @RequestBody AlertSchedule schedule) {
        return alertScheduleService.createSchedule(warrantyId, schedule);
    }

    @GetMapping("/{warrantyId}")
    public List<AlertSchedule> getSchedules(@PathVariable Long warrantyId) {
        return alertScheduleService.getSchedules(warrantyId);
    }
}