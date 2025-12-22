// src/main/java/com/example/demo/service/impl/WarrantyServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.Warranty;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WarrantyRepository;
import com.example.demo.service.WarrantyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarrantyServiceImpl implements WarrantyService {
    private final WarrantyRepository warrantyRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public WarrantyServiceImpl(WarrantyRepository w, UserRepository u, ProductRepository p) {
        this.warrantyRepo = w;
        this.userRepo = u;
        this.productRepo = p;
    }

    @Override
    public Warranty registerWarranty(Long userId, Long productId, Warranty request) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // ✅ Serial number uniqueness check
        if (warrantyRepo.existsBySerialNumber(request.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number must be unique.");
        }

        // ✅ Expiry date validation
        if (request.getExpiryDate() == null || request.getPurchaseDate() == null) {
            throw new IllegalArgumentException("Purchase date and expiry date are required.");
        }
        if (!request.getExpiryDate().isAfter(request.getPurchaseDate())) {
            throw new IllegalArgumentException("Expiry date must be after purchase date.");
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
        return warrantyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warranty not found"));
    }
}