package com.aston.rapidride.service;

import com.aston.rapidride.dto.mapper.UserMapper;
import com.aston.rapidride.dto.request.UserRequest;
import com.aston.rapidride.dto.response.UserResponse;
import com.aston.rapidride.entity.User;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.UserRepository;
import com.aston.rapidride.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private User user;

    @Test
    void getUserById_ifUserNotExist_thenThrowException() {
        Assertions.assertThrows(NotFoundException.class, () -> userServiceImpl.getUserById(1L));
    }

    @Test
    void getUserById_ifUserExist_thenReturnUserDto() {
        UserResponse userResponse = UserResponse.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .build();

        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.mapToResponse(user)).thenReturn(userResponse);

        UserResponse userById = userServiceImpl.getUserById(1L);

        Assertions.assertEquals("Ivan", userById.getFirstName());
        Assertions.assertEquals("Ivanov", userById.getLastName());
    }

    @Test
    void createUser() {
        UserRequest userRequest = UserRequest.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .build();

        User user = new User();
        when(userMapper.mapToEntity(userRequest)).thenReturn(user);

        userServiceImpl.createUser(userRequest);

        verify(userMapper, times(1)).mapToEntity(userRequest);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser() {
        UserRequest userRequest = UserRequest.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .build();
        Long userId = 1L;

        when(userMapper.mapToEntity(userRequest)).thenReturn(user);

        userServiceImpl.updateUser(userRequest, userId);

        verify(userMapper, times(1)).mapToEntity(userRequest);
        verify(userRepository, times(1)).save(user);
        verify(user).setId(userId);
    }

    @Test
    void deleteUser() {
        Long userId = 1L;

        userServiceImpl.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
