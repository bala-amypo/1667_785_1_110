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


        user.setPassword("$2a$" + user.getPassword()); 
        return userRepo.save(user);
    }
}
