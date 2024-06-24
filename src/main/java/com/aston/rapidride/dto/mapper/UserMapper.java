package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.RegisterRequest;
import com.aston.rapidride.dto.response.RegisterResponse;
import com.aston.rapidride.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserMapper {


    private final PasswordEncoder passwordEncoder;



    public User mapToEntity(RegisterRequest registerRequest) {
        return User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phone(registerRequest.getPhone())
                .build();
    }


    public RegisterResponse mapToResponse(User user) {
        return RegisterResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhone())
                .createdDate(LocalDate.now().atStartOfDay())
                .build();
    }
}
