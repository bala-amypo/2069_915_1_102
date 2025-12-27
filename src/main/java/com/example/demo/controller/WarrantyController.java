package com.example.demo.controller;

import com.example.demo.entity.Warranty;
import com.example.demo.service.WarrantyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warranties")
public class WarrantyController {

    private final WarrantyService service;

    public WarrantyController(WarrantyService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/register/{userId}/{productId}")
    public Warranty register(@PathVariable Long userId,
                             @PathVariable Long productId) {
        return service.registerWarranty(userId, productId);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public Warranty get(@PathVariable Long id) {
        return service.getWarranty(id);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/user/{userId}")
    public List<Warranty> byUser(@PathVariable Long userId) {
        return service.getUserWarranties(userId);
    }
}
