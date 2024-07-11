package com.homeheaven.backend.dtos;


import com.homeheaven.backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDto {
    int userId;
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    Role role;

}
