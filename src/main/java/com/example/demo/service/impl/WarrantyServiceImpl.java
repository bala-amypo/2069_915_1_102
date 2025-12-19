// src/main/java/com/example/demo/service/impl/WarrantyServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.WarrantyService;
import java.util.List;

public class WarrantyServiceImpl implements WarrantyService {
    private final WarrantyRepository warrantyRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public WarrantyServiceImpl(WarrantyRepository w, UserRepository u, ProductRepository p) {
        this.warrantyRepo = w; this.userRepo = u; this.productRepo = p;
    }

    @Override
    public Warranty registerWarranty(Long userId, Long productId, Warranty request) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        if (!request.getExpiryDate().isAfter(request.getPurchaseDate())) {
            throw new IllegalArgumentException("Expiry date must be after purchase date");
        }
        if (warrantyRepo.existsBySerialNumber(request.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number must be unique");
        }
        request.setUser(user);
        request.setProduct(product);
        return warrantyRepo.save(request);
    }

    @Override
    public List<Warranty> getUserWarranties(Long userId) {
        return warrantyRepo.findByUserId(userId);
    }

    @Override
    public Warranty getWarranty(Long id) {
        return warrantyRepo.findById(id).orElseThrow(() -> new RuntimeException("Warranty not found"));
    }
}