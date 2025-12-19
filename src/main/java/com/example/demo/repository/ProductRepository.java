// // ProductRepository.java
// package com.example.demo.repository;

// import com.example.demo.entity.Product;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface ProductRepository extends JpaRepository<Product, Long> {
// }

// src/main/java/com/example/demo/repository/ProductRepository.java
package com.example.demo.repository;

import com.example.demo.entity.Product;
import java.util.*;

public interface ProductRepository {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
}