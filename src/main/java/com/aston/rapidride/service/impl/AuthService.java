package com.aston.rapidride.service.impl;

import com.aston.rapidride.Roles;
import com.aston.rapidride.dto.mapper.LoginMapper;
import com.aston.rapidride.dto.mapper.UserMapper;
import com.aston.rapidride.dto.request.LoginRequest;
import com.aston.rapidride.dto.request.RegisterRequest;
import com.aston.rapidride.dto.response.LoginResponse;
import com.aston.rapidride.dto.response.RegisterResponse;
import com.aston.rapidride.entity.User;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.jwt.JwtService;
import com.aston.rapidride.repository.UserRepository;
import com.aston.rapidride.utility.TextConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = userMapper.mapToEntity(registerRequest);
        user.setRoles(Roles.USER.name());
        userRepository.save(user);
        return userMapper.mapToResponse(user);
    }

    public LoginResponse authentication(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new NotFoundException(TextConstants.USER_NOT_FOUND.get()));
        var jwt = jwtService.generateToken(user);
        return LoginMapper.mapToResponse(jwt, user);
    }


    public void resetPassword(String password, String token) throws Exception {
        User user = userRepository.findByToken(token).get();
        if (!checkTimeToken(user.getTokenCreated())) {
            throw new Exception("Token invalid");
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setToken("");
        userRepository.save(user);
    }

    public Boolean checkTimeToken(LocalDateTime tokenCreated) {
        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreated, now);
        return diff.toMinutes() <= 3;
    }

}
