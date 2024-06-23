package com.aston.rapidride.controller;

import com.aston.rapidride.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class TMPUserController {
    private final TMPUserService service;

    @PostMapping
    public User addUser(
            @RequestBody @Valid User user
            ) {
        return service.create(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@RequestParam @Min(1L) Long id) {
        return service.getUserById(id);
    }

}
