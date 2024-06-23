package com.aston.rapidride.repository;

import com.aston.rapidride.entity.Address;
import com.aston.rapidride.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long Id);
    
}
