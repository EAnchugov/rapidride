package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.UserRequest;
import com.aston.rapidride.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    void createUser(UserRequest userRequest);

    void updateUser(UserRequest userRequest, Long id);

    void deleteUser(Long id);
}
