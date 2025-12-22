// src/main/java/com/example/demo/service/impl/ProductServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product addProduct(Product product) {
        // ✅ Validation happens here before saving
        validateProduct(product);
        return repo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    // --- Validation logic is written here ---
    private void validateProduct(Product product) {
        if (product.getModelNumber() == null || product.getModelNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Model number is required and cannot be empty.");
        }

        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Category is required and cannot be empty.");
        }

        // Example: enforce allowed categories
        List<String> allowedCategories = List.of("Electronics", "Furniture", "Clothing");
        if (!allowedCategories.contains(product.getCategory())) {
            throw new IllegalArgumentException("Invalid category. Allowed values: " + allowedCategories);
        }
    }
}