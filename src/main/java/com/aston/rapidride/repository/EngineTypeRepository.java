package com.aston.rapidride.repository;

import com.aston.rapidride.entity.EngineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineTypeRepository extends JpaRepository<EngineType, Long> {

    EngineType findEngineTypeByName(String name);
}
