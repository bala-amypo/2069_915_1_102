package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // ✅ 256-bit secret key (JUnit + jjwt safe)
    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(
                    "THIS_IS_A_256_BIT_SECRET_KEY_FOR_TESTING_PURPOSES_ONLY"
                            .getBytes()
            );

    private static final long EXPIRATION =
            24 * 60 * 60 * 1000; // 24 hours

    public String generateToken(Long id, String email, String role) {

        // ✅ CRITICAL FIX (DO IT HERE, NOT LATER)
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        return Jwts.builder()
                .setSubject(email)
                .claim("id", id)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
