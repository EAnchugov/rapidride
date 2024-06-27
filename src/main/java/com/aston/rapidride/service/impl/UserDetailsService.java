package com.aston.rapidride.service.impl;


import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.UserRepository;
import com.aston.rapidride.utility.TextConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException(TextConstants.USER_NOT_FOUND.get()));
    }
}
