package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RegisterResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
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

    // ✅ Register only saves user, no token returned
    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : "USER")
                .build();

        User saved = userService.register(user);
        return new RegisterResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getRole());
    }

    // ✅ Token is created only on login
    @PostMapping("/login")
public AuthResponse login(@RequestBody AuthRequest request) {
    System.out.println("[DEBUG] login email=" + request.getEmail());

    User user = userService.findByEmail(request.getEmail());
    if (user == null) {
        System.out.println("[DEBUG] user not found in DB");
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    System.out.println("[DEBUG] dbHash=" + user.getPassword());
    boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
    System.out.println("[DEBUG] matches=" + matches);

    if (!matches) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    String token = jwtTokenProvider.createToken(user.getId(), user.getEmail(), user.getRole());
    return new AuthResponse(token, user.getId(), user.getEmail(), user.getRole());
}
}