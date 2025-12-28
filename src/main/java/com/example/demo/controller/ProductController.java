package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products", description = "Manage products in the system")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @Operation(summary = "Add a new product (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Product add(@RequestBody Product product) {
        return repo.save(product);
    }

    @Operation(summary = "Get all products")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }
}