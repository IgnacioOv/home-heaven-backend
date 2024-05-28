package com.homeheaven.backend.service;

import com.homeheaven.backend.controller.auth.AuthenticationRequest;
import com.homeheaven.backend.controller.auth.AuthenticationResponse;
import com.homeheaven.backend.controller.auth.RegisterRequest;
import com.homeheaven.backend.controller.config.JwtService;
import com.homeheaven.backend.entity.User;
import com.homeheaven.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder().firstName(request.getFirstname()).lastName(request.getLastname()).email(request.getEmail()).userPassword(this.passwordEncoder.encode(request.getUserPassword())).role(request.getRole()).username(request.getUsername()).build();


        repository.save(user);
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getUserPassword()));

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
