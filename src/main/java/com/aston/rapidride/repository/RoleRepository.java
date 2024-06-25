package com.aston.rapidride.repository;

import com.aston.rapidride.entity.DocumentType;
import com.aston.rapidride.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
