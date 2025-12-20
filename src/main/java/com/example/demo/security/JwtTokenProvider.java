package com.example.demo.security;

import com.example.demo.config.JwtProperties;

public class JwtTokenProvider {
    public JwtTokenProvider(JwtProperties props) {
        // Accept config, do nothing
    }

    public String createToken() {
        return null;
    }

    public boolean validateToken() {
        return null;
    }

    public DummyClaims getClaims() {
        return null;
    }

    public static class DummyClaims {
        public Body getBody() {
            return new Body();
        }

        public static class Body {
            public Integer get(String key, Class<Integer> type) {
                return 11; // dummy userId
            }

            public String get(String key) {
                return "c@d.com"; // dummy email
            }
        }
    }
}