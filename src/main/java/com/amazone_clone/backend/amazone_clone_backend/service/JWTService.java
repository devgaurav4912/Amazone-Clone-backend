package com.amazone_clone.backend.amazone_clone_backend.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token , UserDetails userDetails);

    public String generateRefreshToken(Map<String ,Object> extraClaims, UserDetails userDetails);
}


