package com.amazone_clone.backend.amazone_clone_backend.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private  String firstName;
    private String lastName;
    private  String email;
    private String password;
}
