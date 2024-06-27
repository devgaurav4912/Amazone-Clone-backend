package com.amazone_clone.backend.amazone_clone_backend.service.impl;

import com.amazone_clone.backend.amazone_clone_backend.dto.JWTAuthenticationResponse;
import com.amazone_clone.backend.amazone_clone_backend.dto.RefreshTokenRequest;
import com.amazone_clone.backend.amazone_clone_backend.dto.SignInRequest;
import com.amazone_clone.backend.amazone_clone_backend.dto.SignUpRequest;
import com.amazone_clone.backend.amazone_clone_backend.entity.Role;
import com.amazone_clone.backend.amazone_clone_backend.entity.User;
import com.amazone_clone.backend.amazone_clone_backend.repository.UserRepository;
import com.amazone_clone.backend.amazone_clone_backend.service.AuthenticationService;
import com.amazone_clone.backend.amazone_clone_backend.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl  implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public User signUp(SignUpRequest signUpRequest){

        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

       return userRepository.save(user);
    }

    public JWTAuthenticationResponse signIn(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                signInRequest.getPassword()));

        var user =userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(
                ()->new IllegalArgumentException("Invalid Email and Password.")
        );

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>() ,user);

        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){

            var jwt = jwtService.generateToken(user);

            JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
