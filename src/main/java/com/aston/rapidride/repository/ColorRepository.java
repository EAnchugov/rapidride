package com.aston.rapidride.repository;

import com.aston.rapidride.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {

    Color findAllByName(String name);
}
