package com.example.demo.controller;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtProvider;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return jwtProvider.createToken(1L, email, "ROLE_USER");
    }
}
