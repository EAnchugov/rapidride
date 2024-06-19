package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.response.CardResponse;
import com.aston.rapidride.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardResponse mapToResponse(Card card) {
        CardResponse response = new CardResponse();
        response.setId(card.getId());
        response.setNumber(card.getNumber());
        response.setOwner(card.getOwner());
        response.setExpireDate(card.getExpireDate());
        response.setUser(card.getUser());

        return response;
    }

    public Card mapRequestToEntity(CardRequest request) {
        Card card = new Card();
        card.setNumber(request.getNumber());
        card.setOwner(request.getOwner());
        card.setExpireDate(request.getExpireDate());
        card.setUser(request.getUser());

        return card;
    }
}
