package com.aston.rapidride.repository;

import com.aston.rapidride.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findCardByNumber(Long number);
    List<Card> findAllByOwner(String owner);
}
