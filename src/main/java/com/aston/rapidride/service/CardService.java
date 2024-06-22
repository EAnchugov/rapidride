package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.response.CardResponse;

import java.util.List;

public interface CardService {

    CardResponse getById(Long id);

    List<CardResponse> getAll();

    void create(CardRequest request);

    void update(Long id, CardRequest request);

    CardResponse getByNumber(Long number);

    List<CardResponse> findAllByOwner(String owner);
}
