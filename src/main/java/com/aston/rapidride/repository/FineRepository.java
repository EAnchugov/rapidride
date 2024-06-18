package com.aston.rapidride.repository;

import com.aston.rapidride.entity.DAO.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine, Long> {
}
