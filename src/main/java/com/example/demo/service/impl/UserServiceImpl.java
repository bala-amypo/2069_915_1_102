package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository repo) {
        this.userRepository = repo;
    }

    @Override
    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // ✅ Role validation
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER"); // default role
        } else {
            String role = user.getRole().toUpperCase();
            if (!role.equals("USER") && !role.equals("ADMIN")) {
                throw new IllegalArgumentException("Role must be either USER or ADMIN");
            }
            user.setRole(role); // normalize to uppercase
        }

        // Password handling left as-is
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}