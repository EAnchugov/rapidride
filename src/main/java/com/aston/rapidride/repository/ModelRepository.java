package com.aston.rapidride.repository;

import com.aston.rapidride.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {

    Model findModelByName(String name);
}
