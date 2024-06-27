package com.amazone_clone.backend.amazone_clone_backend.controller;

import com.amazone_clone.backend.amazone_clone_backend.dto.JWTAuthenticationResponse;
import com.amazone_clone.backend.amazone_clone_backend.dto.RefreshTokenRequest;
import com.amazone_clone.backend.amazone_clone_backend.dto.SignInRequest;
import com.amazone_clone.backend.amazone_clone_backend.dto.SignUpRequest;
import com.amazone_clone.backend.amazone_clone_backend.entity.User;
import com.amazone_clone.backend.amazone_clone_backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }
    
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
