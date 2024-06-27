package com.amazone_clone.backend.amazone_clone_backend.service;

import com.amazone_clone.backend.amazone_clone_backend.dto.JWTAuthenticationResponse;
import com.amazone_clone.backend.amazone_clone_backend.dto.RefreshTokenRequest;
import com.amazone_clone.backend.amazone_clone_backend.dto.SignInRequest;
import com.amazone_clone.backend.amazone_clone_backend.dto.SignUpRequest;
import com.amazone_clone.backend.amazone_clone_backend.entity.User;

public interface AuthenticationService {

    public User signUp(SignUpRequest signUpRequest);

    public JWTAuthenticationResponse signIn(SignInRequest signInRequest);

    public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
