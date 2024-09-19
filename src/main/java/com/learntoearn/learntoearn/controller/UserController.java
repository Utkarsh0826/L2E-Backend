package com.learntoearn.learntoearn.controller;

import com.learntoearn.learntoearn.DTO.UserResponseDTO;
import com.learntoearn.learntoearn.model.User;
import com.learntoearn.learntoearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody User user) {
        try {
            // Check if email is already registered
            Optional<UserResponseDTO> existingUser = userService.authenticateUser(user.getEmail(), user.getPassword());
            if (existingUser.isPresent()) {
                return ResponseEntity.badRequest().body("Email is already registered.");
            }
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<UserResponseDTO> user = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
