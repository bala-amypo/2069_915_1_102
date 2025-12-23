
2.	// src/main/java/com/example/demo/config/JwtProperties.java
3.	package com.example.demo.config;
4.	
5.	import org.springframework.boot.context.properties.ConfigurationProperties;
6.	import org.springframework.context.annotation.Configuration;
7.	
8.	@Configuration
9.	@ConfigurationProperties(prefix = "jwt")
10.	public class JwtProperties {
11.	
12.	    private String secret;
13.	    private long expirationMs;
14.	
15.	    public String getSecret() {
16.	        return secret;
17.	    }
18.	
19.	    public void setSecret(String secret) {
20.	        this.secret = secret;
21.	    }
22.	
23.	    public long getExpirationMs() {
24.	        return expirationMs;
25.	    }
26.	
27.	    public void setExpirationMs(long expirationMs) {
28.	        this.expirationMs = expirationMs;
29.	    }
30.	}
