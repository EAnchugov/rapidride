package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.LoginRequest;
import com.aston.rapidride.dto.request.UserRequest;
import com.aston.rapidride.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sing-up")
    public ResponseEntity<?> register(@RequestBody UserRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(authService.authentication(request), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String password, String token) {
        try {
            authService.resetPassword(password, token);
        } catch (Exception e) {
            return new ResponseEntity<>("Token invalid", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Password updated", HttpStatus.OK);
    }
}
