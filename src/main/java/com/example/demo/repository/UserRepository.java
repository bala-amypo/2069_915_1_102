// // UserRepository.java
// package com.example.demo.repository;

// import com.example.demo.entity.User;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.Optional;

// @Repository
// public interface UserRepository extends JpaRepository<User, Long> {
//     boolean existsByEmail(String email);
//     Optional<User> findByEmail(String email);  // <-- Add this
// }

// src/main/java/com/example/demo/repository/UserRepository.java
package com.example.demo.repository;

import com.example.demo.entity.User;
import java.util.*;

public interface UserRepository {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    User save(User user);
}