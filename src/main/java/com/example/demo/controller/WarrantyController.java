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
    public Warranty registerWarranty(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestBody Warranty warranty
    ) {
        return service.registerWarranty(userId, productId, warranty);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public Warranty getWarranty(@PathVariable Long id) {
        return service.getWarranty(id);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/user/{userId}")
    public List<Warranty> getUserWarranties(@PathVariable Long userId) {
        return service.getUserWarranties(userId);
    }
}
