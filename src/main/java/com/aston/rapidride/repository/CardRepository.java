package com.aston.rapidride.repository;

import com.aston.rapidride.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Card findCardByNumber(Long number);

    Card findCardByOwner(String owner);
}
