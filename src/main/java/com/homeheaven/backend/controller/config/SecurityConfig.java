package com.homeheaven.backend.controller.config;

import com.homeheaven.backend.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        //ORDERS
                        .requestMatchers("/orders/add").authenticated()
                        .requestMatchers("/orders/{buyerId}").authenticated()
                        //PRODUCT ORDER
                        .requestMatchers("/productOrder/{sellerId}").authenticated()
                        .requestMatchers("/productOrder/add").authenticated()
                        //.requestMatchers("/productOrder/recommended")ANYONE
                        //PRODUCTS
                        .requestMatchers("/products/add").hasAuthority(Role.SELLER.name())
                        .requestMatchers("/products/edit/{productId}").hasAuthority(Role.SELLER.name())
                        .requestMatchers("/products/delete/{productId}").hasAuthority(Role.SELLER.name())
                        //.requestMatchers("/products/{productId}") ANYONE
                        //.requestMatchers("/products//all") ANYONE
                        //.requestMatchers("/products/category/{category}") ANYONE
                        //.requestMatchers("/products/search/{param}") ANYONE



                        .anyRequest()
                        .permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
