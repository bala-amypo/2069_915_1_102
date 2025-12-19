// // // ProductServiceImpl.java
// // package com.example.demo.service.impl;

// // import com.example.demo.entity.Product;
// // import com.example.demo.repository.ProductRepository;
// // import com.example.demo.service.ProductService;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.stereotype.Service;

// // import java.util.List;

// // @Service
// // public class ProductServiceImpl implements ProductService {

// //     @Autowired
// //     private ProductRepository productRepository;

// //     @Override
// //     public Product addProduct(Product product) {
// //         if (product.getModelNumber() == null || product.getModelNumber().isBlank()) {
// //             throw new RuntimeException("Model number required");
// //         }
// //         if (product.getCategory() == null || product.getCategory().isBlank()) {
// //             throw new RuntimeException("Category required");
// //         }
// //         return productRepository.save(product);
// //     }

// //     @Override
// //     public List<Product> getAllProducts() {
// //         return productRepository.findAll();
// //     }
// // }

// package com.example.demo.service.impl;

// import com.example.demo.entity.Product;
// import com.example.demo.repository.ProductRepository;
// import com.example.demo.service.ProductService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class ProductServiceImpl implements ProductService {

//     @Autowired
//     private ProductRepository productRepository;

//     @Override
//     public Product addProduct(Product product) {
//         return productRepository.save(product);
//     }

//     @Override
//     public List<Product> getAllProducts() {
//         return productRepository.findAll();
//     }
// }

// src/main/java/com/example/demo/service/impl/ProductServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) { this.productRepository = productRepository; }

    @Override
    public Product addProduct(Product product) {
        if (product.getModelNumber() == null || product.getModelNumber().isBlank()) {
            throw new IllegalArgumentException("Model number required");
        }
        if (product.getCategory() == null || product.getCategory().isBlank()) {
            throw new IllegalArgumentException("Category required");
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}