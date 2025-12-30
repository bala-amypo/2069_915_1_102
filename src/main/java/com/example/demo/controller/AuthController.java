package com.example.demo.controller;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;

    public AuthController(
            AuthenticationManager authManager,
            JwtTokenProvider jwtProvider) {

        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        return jwtProvider.createToken(1L, email, "ROLE_USER");
    }
}
