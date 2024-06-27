package com.amazone_clone.backend.amazone_clone_backend.dto;

import lombok.Data;

@Data
public class JWTAuthenticationResponse {
    private String token;
    private String refreshToken;
}
