package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService,
                          UserRepository userRepository,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // 🔹 REGISTER
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    // 🔹 LOGIN
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User login) {

        User stored = userRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // SIMPLE password check (matches TestNG simulation)
        if (!stored.getPassword().contains(login.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                stored.getId(),
                stored.getEmail(),
                stored.getRole()
        );

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
