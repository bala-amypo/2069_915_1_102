package com.example.demo.controller;

import com.example.demo.entity.AlertLog;
import com.example.demo.service.AlertLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class AlertLogController {

    @Autowired
    private AlertLogService alertLogService;

    // POST /logs/{warrantyId} — add log message
    @PostMapping("/{warrantyId}")
    public AlertLog addLog(@PathVariable Long warrantyId, @RequestBody String message) {
        return alertLogService.addLog(warrantyId, message);
    }

    // GET /logs/{warrantyId} — list logs for warranty
    @GetMapping("/{warrantyId}")
    public List<AlertLog> getLogs(@PathVariable Long warrantyId) {
        return alertLogService.getLogs(warrantyId);
    }
}