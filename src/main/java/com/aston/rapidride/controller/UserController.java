package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.UserRequest;
import com.aston.rapidride.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        userService.updateUser(userRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
