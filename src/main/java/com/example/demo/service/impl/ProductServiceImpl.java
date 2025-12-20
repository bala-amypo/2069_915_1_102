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

  public ProductServiceImpl(ProductRepository repo) { this.repo = repo; }

  @Override
  public Product addProduct(Product product) {
    if (product.getModelNumber() == null || product.getModelNumber().isBlank()) {
      throw new IllegalArgumentException("Model number required");
    }
    if (product.getCategory() == null || product.getCategory().isBlank()) {
      throw new IllegalArgumentException("Category required");
    }
    return repo.save(product);
  }

  @Override
  public List<Product> getAllProducts() {
    return repo.findAll();
  }
}