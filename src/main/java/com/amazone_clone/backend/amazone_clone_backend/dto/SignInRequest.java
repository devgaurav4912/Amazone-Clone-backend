package com.amazone_clone.backend.amazone_clone_backend.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private  String email;
    private String password;
}
