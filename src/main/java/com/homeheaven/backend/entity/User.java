package com.homeheaven.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "user_password")
    private String userPassword;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role;

}
