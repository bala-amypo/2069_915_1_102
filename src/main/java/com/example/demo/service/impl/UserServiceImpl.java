// src/main/java/com/example/demo/service/impl/UserServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

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
    // Save password exactly as provided (no encoding/encryption)
    // Just ensure it's not null
    if (user.getPassword() != null) {
      user.setPassword(user.getPassword());
    }
    return userRepository.save(user);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }
}