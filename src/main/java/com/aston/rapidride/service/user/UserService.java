package com.aston.rapidride.service.user;

import com.aston.rapidride.dto.mapper.user.UserMapper;
import com.aston.rapidride.dto.user.UserDto;
import com.aston.rapidride.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getAllUsers() {
       return userRepository.findAll()
               .stream()
               .map(userMapper::entityToUser)
               .toList();
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::entityToUser)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " is not found"));
    }

}
