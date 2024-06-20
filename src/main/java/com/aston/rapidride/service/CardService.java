package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.response.CardResponse;
import com.aston.rapidride.entity.Card;

import java.util.List;

public interface CardService {

    CardResponse getById(Long id);

    List<CardResponse> getAll();

    void create(CardRequest request);

    void update(Long id, CardRequest request);

    CardResponse getCardByNumber(Long number);

    CardResponse getCardByOwner(String owner);
}
