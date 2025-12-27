package com.example.demo.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.config.JwtProperties;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private final JwtProperties properties;
    private final Key signingKey;

    public JwtTokenProvider(JwtProperties properties) {
        this.properties = properties;

        // 🔑 CORRECT WAY: force 256-bit HS256 key
        byte[] keyBytes = properties.getSecret().getBytes(java.nio.charset.StandardCharsets.UTF_8);

        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 32 characters for HS256");
        }

        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    // -------------------------------
    // CREATE TOKEN
    // -------------------------------
    public String createToken(Long userId, String email, String role) {

        Date now = new Date();
        Date expiry = new Date(now.getTime() + properties.getExpirationMs());

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // -------------------------------
    // VALIDATE TOKEN
    // -------------------------------
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    // -------------------------------
    // GET CLAIMS
    // -------------------------------
    public Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token);
    }
}
