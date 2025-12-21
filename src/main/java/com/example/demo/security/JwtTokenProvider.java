package com.example.demo.security;
public class JwtTokenProvider {
    public JwtTokenProvider(JwtProperties props) {
        // Accept config, do nothing
    }

    public String createToken(Long userId, String email, String role) {
        return null;
    }

    public boolean validateToken(String token) {
        return false; // or return true if you prefer, but null is not valid for boolean
    }

    public Claims getClaims(String token) {
        return null;
    }

    // Inner placeholder class
    public static class Claims {
        public Body getBody() {
            return null;
        }
    }

    // Inner placeholder class
    public static class Body {
        public Integer get(String key, Class<Integer> type) {
            return null;
        }

        public String get(String key) {
            return null;
        }
    }
}