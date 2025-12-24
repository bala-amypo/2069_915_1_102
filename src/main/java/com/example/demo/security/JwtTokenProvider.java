// src/main/java/com/example/demo/security/JwtTokenProvider.java
package com.example.demo.security;

import com.example.demo.config.JwtProperties;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    public JwtTokenProvider(JwtProperties properties) {
        this.jwtProperties = properties;
    }

    public String createToken(Long userId, String email, String role) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + jwtProperties.getExpirationMs());

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                // Store userId as Integer to match tests retrieving Integer.class
                .claim("userId", userId.intValue())
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret()) // 0.9.1 style
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(jwtProperties.getSecret()) // 0.9.1 style
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Return Jws<Claims> so tests can call .getBody()
    public Jws<Claims> getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret()) // 0.9.1 style
                .parseClaimsJws(token);
    }
}