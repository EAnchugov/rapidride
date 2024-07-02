package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.UserMapper;
import com.aston.rapidride.dto.request.UserRequest;
import com.aston.rapidride.dto.response.UserResponse;
import com.aston.rapidride.entity.User;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.UserRepository;
import com.aston.rapidride.utility.TextConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserById_ShouldReturnUserResponse_WhenUserExists() {
        Long userId = 1L;
        User user = new User();
        UserResponse userResponse = new UserResponse();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.mapToResponse(user)).thenReturn(userResponse);

        UserResponse result = userService.getUserById(userId);

        assertEquals(userResponse, result);
        verify(userRepository).findById(userId);
        verify(userMapper).mapToResponse(user);
    }

    @Test
    void getUserById_ShouldThrowNotFoundException_WhenUserDoesNotExist() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.getUserById(userId));
        assertEquals(TextConstants.USER_NOT_FOUND.get(), exception.getMessage());
        verify(userRepository).findById(userId);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUserResponses() {
        List<User> users = List.of(new User());
        List<UserResponse> userResponses = List.of(new UserResponse());

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.mapToResponse(any(User.class))).thenReturn(userResponses.get(0));

        List<UserResponse> result = userService.getAllUsers();

        assertEquals(userResponses, result);
        verify(userRepository).findAll();
        verify(userMapper, times(users.size())).mapToResponse(any(User.class));
    }

    @Test
    void createUser_ShouldSaveUser() {
        UserRequest userRequest = new UserRequest();
        User user = new User();

        when(userMapper.mapToEntity(userRequest)).thenReturn(user);

        userService.createUser(userRequest);

        verify(userRepository).save(user);
    }

    @Test
    void updateUser_ShouldUpdateUser() {
        Long userId = 1L;
        UserRequest userRequest = new UserRequest();
        User user = new User();

        when(userMapper.mapToEntity(userRequest)).thenReturn(user);

        userService.updateUser(userRequest, userId);

        verify(userMapper).mapToEntity(userRequest);
        verify(userRepository).save(user);
        assertEquals(userId, user.getId());
    }

    @Test
    void deleteUser_ShouldDeleteUser() {
        Long userId = 1L;

        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }
}
