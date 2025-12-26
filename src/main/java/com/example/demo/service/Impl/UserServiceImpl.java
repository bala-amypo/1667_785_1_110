package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ValidationException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User registerUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new ValidationException("User already exists");
        }
        return userRepo.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new ValidationException("User not found");
        }
        return user;
    }
}
