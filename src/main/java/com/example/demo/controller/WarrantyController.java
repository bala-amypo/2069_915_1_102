package com.example.demo.controller;

import com.example.demo.entity.Warranty;
import com.example.demo.service.WarrantyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Warranties", description = "Manage warranties for products")
@RestController
@RequestMapping("/warranties")
public class WarrantyController {

    private final WarrantyService service;

    public WarrantyController(WarrantyService service) {
        this.service = service;
    }

    @Operation(summary = "Register a new warranty for a product")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/register/{userId}/{productId}")
    public Warranty registerWarranty(@PathVariable Long userId, @PathVariable Long productId, @RequestBody Warranty warranty) {
        return service.registerWarranty(userId, productId, warranty);
    }

    @Operation(summary = "Get warranty by ID")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public Warranty getWarranty(@PathVariable Long id) {
        return service.getWarranty(id);
    }

    @Operation(summary = "Get all warranties for a user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/user/{userId}")
    public List<Warranty> getUserWarranties(@PathVariable Long userId) {
        return service.getUserWarranties(userId);
    }
}