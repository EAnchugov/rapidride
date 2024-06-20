package com.aston.rapidride.repository;

import com.aston.rapidride.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findBrandByName(String name);
}
