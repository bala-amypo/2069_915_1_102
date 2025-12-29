package com.example.demo.controller;

import com.example.demo.entity.AlertLog;
import com.example.demo.service.AlertLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Alert Logs", description = "Manage alert logs for warranties")
@RestController
@RequestMapping("/logs")
public class AlertLogController {

    private final AlertLogService alertLogService;

    public AlertLogController(AlertLogService alertLogService) {
        this.alertLogService = alertLogService;
    }

    @Operation(summary = "Add a new log entry for a warranty (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{warrantyId}")
    public AlertLog addLog(
            @PathVariable Long warrantyId,
            @RequestBody String message
    ) {
        return alertLogService.addLog(warrantyId, message);
    }

    
    @Operation(summary = "Get all logs for a warranty (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{warrantyId}")
    public List<AlertLog> getLogs(@PathVariable Long warrantyId) {
        return alertLogService.getLogs(warrantyId);
    }
}
