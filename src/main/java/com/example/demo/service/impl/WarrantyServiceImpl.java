// // // WarrantyServiceImpl.java
// // package com.example.demo.service.impl;

// // import com.example.demo.entity.Product;
// // import com.example.demo.entity.User;
// // import com.example.demo.entity.Warranty;
// // import com.example.demo.repository.ProductRepository;
// // import com.example.demo.repository.UserRepository;
// // import com.example.demo.repository.WarrantyRepository;
// // import com.example.demo.service.WarrantyService;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.stereotype.Service;

// // import java.util.List;

// // @Service
// // public class WarrantyServiceImpl implements WarrantyService {

// //     @Autowired private WarrantyRepository warrantyRepository;
// //     @Autowired private UserRepository userRepository;
// //     @Autowired private ProductRepository productRepository;

// //     @Override
// //     public Warranty registerWarranty(Long userId, Long productId, Warranty warranty) {
// //         User user = userRepository.findById(userId)
// //                 .orElseThrow(() -> new RuntimeException("User not found"));
// //         Product product = productRepository.findById(productId)
// //                 .orElseThrow(() -> new RuntimeException("Product not found"));

// //         warranty.setUser(user);
// //         warranty.setProduct(product);

// //         if (warranty.getExpiryDate().isBefore(warranty.getPurchaseDate())) {
// //             throw new RuntimeException("Expiry date must be after purchase date");
// //         }

// //         return warrantyRepository.save(warranty);
// //     }

// //     @Override
// //     public Warranty getWarranty(Long warrantyId) {
// //         return warrantyRepository.findById(warrantyId)
// //                 .orElseThrow(() -> new RuntimeException("Warranty not found"));
// //     }

// //     @Override
// //     public List<Warranty> getUserWarranties(Long userId) {
// //         User user = userRepository.findById(userId)
// //                 .orElseThrow(() -> new RuntimeException("User not found"));
// //         return warrantyRepository.findAll()
// //                 .stream()
// //                 .filter(w -> w.getUser().equals(user))
// //                 .toList();
// //     }
// // }

// package com.example.demo.service.impl;

// import com.example.demo.entity.Product;
// import com.example.demo.entity.User;
// import com.example.demo.entity.Warranty;
// import com.example.demo.repository.ProductRepository;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.repository.WarrantyRepository;
// import com.example.demo.service.WarrantyService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class WarrantyServiceImpl implements WarrantyService {

//     @Autowired private WarrantyRepository warrantyRepository;
//     @Autowired private UserRepository userRepository;
//     @Autowired private ProductRepository productRepository;

//     @Override
//     public Warranty registerWarranty(Long userId, Long productId, Warranty warranty) {
//         User user = userRepository.findById(userId).get();
//         Product product = productRepository.findById(productId).get();
//         warranty.setUser(user);
//         warranty.setProduct(product);
//         return warrantyRepository.save(warranty);
//     }

//     @Override
//     public Warranty getWarranty(Long warrantyId) {
//         return warrantyRepository.findById(warrantyId).get();
//     }

//     @Override
//     public List<Warranty> getUserWarranties(Long userId) {
//         return warrantyRepository.findAll();
//     }
// }

// src/main/java/com/example/demo/service/impl/WarrantyServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.WarrantyService;

import java.util.List;

public class WarrantyServiceImpl implements WarrantyService {

    private final WarrantyRepository warrantyRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WarrantyServiceImpl(WarrantyRepository warrantyRepository,
                               UserRepository userRepository,
                               ProductRepository productRepository) {
        this.warrantyRepository = warrantyRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Warranty registerWarranty(Long userId, Long productId, Warranty request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        if (request.getPurchaseDate() == null || request.getExpiryDate() == null ||
            !request.getExpiryDate().isAfter(request.getPurchaseDate())) {
            throw new IllegalArgumentException("Expiry date must be after purchase date");
        }
        if (request.getSerialNumber() == null || request.getSerialNumber().isBlank()) {
            throw new IllegalArgumentException("Serial number required");
        }
        if (warrantyRepository.existsBySerialNumber(request.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number must be unique");
        }
        request.setUser(user);
        request.setProduct(product);
        return warrantyRepository.save(request);
    }

    @Override
    public List<Warranty> getUserWarranties(Long userId) {
        return warrantyRepository.findByUserId(userId);
    }

    @Override
    public Warranty getWarranty(Long id) {
        return warrantyRepository.findById(id).orElseThrow(() -> new RuntimeException("Warranty not found"));
    }
}