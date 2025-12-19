package com.example.demo.controller;

import com.example.demo.entity.Warranty;
import com.example.demo.service.WarrantyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warranties")
public class WarrantyController {

    @Autowired
    private WarrantyService warrantyService;

    // POST /warranties/register/{userId}/{productId} — register warranty for user+product
    @PostMapping("/register/{userId}/{productId}")
    public Warranty registerWarranty(@PathVariable Long userId,
                                     @PathVariable Long productId,
                                     @RequestBody Warranty request) {
        return warrantyService.registerWarranty(userId, productId, request);
    }

    // GET /warranties/{warrantyId} — get single warranty
    @GetMapping("/{warrantyId}")
    public Warranty getWarranty(@PathVariable Long warrantyId) {
        return warrantyService.getWarranty(warrantyId);
    }

    // GET /warranties/user/{userId} — list warranties for user
    @GetMapping("/user/{userId}")
    public List<Warranty> getUserWarranties(@PathVariable Long userId) {
        return warrantyService.getUserWarranties(userId);
    }
}