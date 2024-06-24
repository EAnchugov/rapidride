package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.CardMapper;
import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.response.CardResponse;
import com.aston.rapidride.entity.Card;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.CardRepository;
import com.aston.rapidride.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aston.rapidride.utility.TextConstants.CARD_NOT_FOUND;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository repository;
    private final CardMapper mapper;

    @Override
    public CardResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToResponse)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND.get()));
    }

    @Override
    public List<CardResponse> getAll() {
        List<Card> cards = repository.findAll();

        return cards.stream()
                .map(mapper::mapToResponse)
                .toList();
    }

    @Override
    public void create(CardRequest request) {
        repository.save(mapper.mapRequestToEntity(request));
    }

    @Override
    public void update(Long id, CardRequest request) {
        Card card = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND.get()));
        card.setNumber(request.getNumber());
        card.setOwner(request.getOwner());
        card.setExpireDate(request.getExpireDate());
        card.setUser(request.getUser());
        repository.save(card);
    }

    @Override
    public CardResponse getByNumber(Long number) {
        Card card = repository.findCardByNumber(number);

        if (card != null) {
            return mapper.mapToResponse(card);
        } else {
            throw new NotFoundException(CARD_NOT_FOUND.get());
        }
    }

    @Override
    public List<CardResponse> findAllByOwner(String owner) {
        List<Card> cards = repository.findAllByOwner(owner);

        if (!cards.isEmpty()) {
            return cards.stream()
                    .map(mapper::mapToResponse)
                    .toList();
        } else {
            throw new NotFoundException(CARD_NOT_FOUND.get());
        }
    }
}
