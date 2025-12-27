package com.example.demo.security;

import com.example.demo.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String createToken(Long userId, String email, String role) {

        Date now = new Date();
        Date expiry = new Date(
                now.getTime() + jwtProperties.getExpirationMs()
        );

        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(
                        SignatureAlgorithm.HS256,
                        jwtProperties.getSecret()
                )
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token);
    }
}
