// AlertScheduleController.java
package com.example.demo.controller;

import com.example.demo.entity.AlertSchedule;
import com.example.demo.service.AlertScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertSchedules")
public class AlertScheduleController {

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