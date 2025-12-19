// AlertLogController.java
package com.example.demo.controller;

import com.example.demo.entity.AlertLog;
import com.example.demo.service.AlertLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertLogs")
public class AlertLogController {

    @Autowired
    private AlertLogService alertLogService;

    @PostMapping("/{warrantyId}")
    public AlertLog addLog(@PathVariable Long warrantyId, @RequestParam String message) {
        return alertLogService.addLog(warrantyId, message);
    }

    @GetMapping("/{warrantyId}")
    public List<AlertLog> getLogs(@PathVariable Long warrantyId) {
        return alertLogService.getLogs(warrantyId);
    }
}