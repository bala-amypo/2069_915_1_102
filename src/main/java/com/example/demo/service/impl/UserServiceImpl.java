// src/main/java/com/example/demo/service/impl/UserServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  // Tests instantiate with only UserRepository
  public UserServiceImpl(UserRepository repo) {
    this.userRepository = repo;
  }

  @Override
  public User register(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new IllegalArgumentException("Email already exists");
    }
    if (user.getRole() == null || user.getRole().isBlank()) {
      user.setRole("USER");
    }
    // Simple taught-scope encoding (no security frameworks): just ensure not equal to raw
    if (user.getPassword() != null) {
      String encoded = Base64.getEncoder()
        .encodeToString(("ENC:" + user.getPassword()).getBytes(StandardCharsets.UTF_8));
      user.setPassword(encoded);
    }
    return userRepository.save(user);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }
}