package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.UserMapper;
import com.aston.rapidride.dto.request.UserRequest;
import com.aston.rapidride.dto.response.UserResponse;
import com.aston.rapidride.entity.User;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.UserRepository;
import com.aston.rapidride.service.UserService;
import com.aston.rapidride.utility.TextConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapToResponse)
                .orElseThrow(() -> new NotFoundException(TextConstants.USER_NOT_FOUND.get()));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToResponse)
                .toList();
    }

    @Override
    public void createUser(UserRequest userRequest) {
        userRepository.save(userMapper.mapToEntity(userRequest));
    }

    @Override
    public void updateUser(UserRequest userRequest, Long id) {
        User user = userMapper.mapToEntity(userRequest);
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
