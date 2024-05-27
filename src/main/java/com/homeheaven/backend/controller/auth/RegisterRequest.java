package com.homeheaven.backend.controller.auth;
import com.homeheaven.backend.entity.Role;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String userPassword;
    private Role role;
}
