package com.aston.rapidride.repository.user;

import com.aston.rapidride.entity.Address;
import com.aston.rapidride.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
