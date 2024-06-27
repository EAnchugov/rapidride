package com.aston.rapidride.dto.mapper;


import com.aston.rapidride.dto.response.LoginResponse;
import com.aston.rapidride.entity.User;


public class LoginMapper {

    public static LoginResponse mapToResponse(String token, User user) {

        return LoginResponse.builder()
                .email(user.getEmail())
                .token(token)
                .build();
    }
}
