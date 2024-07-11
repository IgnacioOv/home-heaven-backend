package com.homeheaven.backend.service;

import com.homeheaven.backend.dtos.UpdateUserDto;
import com.homeheaven.backend.entity.User;
import com.homeheaven.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    public ResponseEntity<User> editUser(UpdateUserDto updateUserDto) {

        User user = userRepository.findById(updateUserDto.getUserId()).orElse(null);
        if (user == null) {
            return null;
        }
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setEmail(updateUserDto.getEmail());
        user.setRole(updateUserDto.getRole());
        user.setUserPassword(passwordEncoder.encode(updateUserDto.getPassword()));

        return ResponseEntity.ok(userRepository.save(user));
    }
}
