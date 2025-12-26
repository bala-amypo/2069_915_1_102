package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping
public class AuthController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService,
                          JwtTokenProvider jwtTokenProvider,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    // Map both /auth/register and /swagger-ui/index.html/auth/register
    @PostMapping({"/auth/register", "/swagger-ui/index.html/auth/register"})
    public AuthResponse register(@RequestBody RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : "USER")
                .build();

        User saved = userService.register(user);

        // Generate JWT token for the newly registered user
        String token = jwtTokenProvider.createToken(saved.getId(), saved.getEmail(), saved.getRole());

        // Return token + user info
        return new AuthResponse(token, saved.getId(), saved.getEmail(), saved.getRole());
    }

    // Map both /auth/login and /swagger-ui/index.html/auth/login
    @PostMapping({"/auth/login", "/swagger-ui/index.html/auth/login"})
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = userService.findByEmail(request.getEmail());
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtTokenProvider.createToken(user.getId(), user.getEmail(), user.getRole());
            return new AuthResponse(token, user.getId(), user.getEmail(), user.getRole());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }
}