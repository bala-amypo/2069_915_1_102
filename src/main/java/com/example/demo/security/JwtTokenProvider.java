package com.example.demo.security;

import com.example.demo.config.JwtProperties;

public class JwtTokenProvider {
    public JwtTokenProvider(JwtProperties props) {
        // Accept config, do nothing
    }

    public String createToken(Long userId, String email, String role) {
        return null;
    }

    public boolean validateToken(String token) {
        return true;
    }

    public String getClaims(String token) {
        return null;
    }

}