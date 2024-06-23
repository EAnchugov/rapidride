package com.aston.rapidride.controller;

import com.aston.rapidride.entity.User;
import com.aston.rapidride.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TMPUserService {

    UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
