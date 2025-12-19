package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // POST /auth/register — builds User from request and delegates registration
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        // Default role to USER since RegisterRequest has no role field
        User user = new User(null, request.getName(), request.getEmail(), request.getPassword(), "USER");
        return userService.register(user);
    }

    // POST /auth/login — verifies password and returns JWT + role
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = userService.findByEmail(request.getEmail());
        boolean ok = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (ok) {
            String token = jwtTokenProvider.createToken(user.getId(), user.getEmail(), user.getRole());
            return new AuthResponse(token, user.getRole());
        }
        // Minimal: return empty token/role on bad credentials
        return new AuthResponse(null, null);
    }
}