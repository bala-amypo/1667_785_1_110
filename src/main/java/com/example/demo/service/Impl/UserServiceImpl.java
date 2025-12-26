package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;



    

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepo) {
        this.encoder = encoder;
        this.userRepo = userRepo;
    }
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
        this.encoder = new BCryptPasswordEncoder(); // or null if test doesn't encode
    }

    @Override
    public User registerUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new ValidationException("User already exists");
        }

       user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ValidationException("User not found"));
    }
}
