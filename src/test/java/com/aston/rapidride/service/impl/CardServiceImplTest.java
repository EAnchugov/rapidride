package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.CardMapper;
import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.response.CardResponse;
import com.aston.rapidride.entity.Card;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.aston.rapidride.utility.TextConstants.CARD_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceImplTest {

    @Mock
    private CardRepository repository;

    @Mock
    private CardMapper mapper;

    @InjectMocks
    private CardServiceImpl cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById_ShouldReturnCardResponse_WhenCardExists() {
        Long cardId = 1L;
        Card card = new Card();
        CardResponse cardResponse = new CardResponse();

        when(repository.findById(cardId)).thenReturn(Optional.of(card));
        when(mapper.mapToResponse(card)).thenReturn(cardResponse);

        CardResponse result = cardService.getById(cardId);

        assertEquals(cardResponse, result);
        verify(repository).findById(cardId);
        verify(mapper).mapToResponse(card);
    }

    @Test
    void getById_ShouldThrowNotFoundException_WhenCardDoesNotExist() {
        Long cardId = 1L;

        when(repository.findById(cardId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> cardService.getById(cardId));
        assertEquals(CARD_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(cardId);
    }

    @Test
    void getAll_ShouldReturnListOfCardResponses() {
        List<Card> cards = List.of(new Card());
        List<CardResponse> cardResponses = List.of(new CardResponse());

        when(repository.findAll()).thenReturn(cards);
        when(mapper.mapToResponse(any(Card.class))).thenReturn(cardResponses.get(0));

        List<CardResponse> result = cardService.getAll();

        assertEquals(cardResponses, result);
        verify(repository).findAll();
        verify(mapper).mapToResponse(any(Card.class));
    }

    @Test
    void create_ShouldSaveCard() {
        CardRequest request = new CardRequest();
        Card card = new Card();

        when(mapper.mapRequestToEntity(request)).thenReturn(card);

        cardService.create(request);

        verify(repository).save(card);
    }

    @Test
    void update_ShouldUpdateCard_WhenCardExists() {
        Long cardId = 1L;
        CardRequest request = new CardRequest();
        Card card = new Card();

        when(repository.findById(cardId)).thenReturn(Optional.of(card));

        cardService.update(cardId, request);

        verify(repository).findById(cardId);
        verify(repository).save(card);
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenCardDoesNotExist() {
        Long cardId = 1L;
        CardRequest request = new CardRequest();

        when(repository.findById(cardId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> cardService.update(cardId, request));
        assertEquals(CARD_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(cardId);
    }

    @Test
    void getByNumber_ShouldReturnCardResponse_WhenCardExists() {
        Long cardNumber = 123456789L;
        Card card = new Card();
        CardResponse cardResponse = new CardResponse();

        when(repository.findCardByNumber(cardNumber)).thenReturn(card);
        when(mapper.mapToResponse(card)).thenReturn(cardResponse);

        CardResponse result = cardService.getByNumber(cardNumber);

        assertEquals(cardResponse, result);
        verify(repository).findCardByNumber(cardNumber);
        verify(mapper).mapToResponse(card);
    }

    @Test
    void getByNumber_ShouldThrowNotFoundException_WhenCardDoesNotExist() {
        Long cardNumber = 123456789L;

        when(repository.findCardByNumber(cardNumber)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> cardService.getByNumber(cardNumber));
        assertEquals(CARD_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findCardByNumber(cardNumber);
    }

    @Test
    void findAllByOwner_ShouldReturnListOfCardResponses_WhenCardsExist() {
        String owner = "John Doe";
        List<Card> cards = List.of(new Card());
        List<CardResponse> cardResponses = List.of(new CardResponse());

        when(repository.findAllByOwner(owner)).thenReturn(cards);
        when(mapper.mapToResponse(any(Card.class))).thenReturn(cardResponses.get(0));

        List<CardResponse> result = cardService.findAllByOwner(owner);

        assertEquals(cardResponses, result);
        verify(repository).findAllByOwner(owner);
        verify(mapper).mapToResponse(any(Card.class));
    }

    @Test
    void findAllByOwner_ShouldThrowNotFoundException_WhenCardsDoNotExist() {
        String owner = "John Doe";

        when(repository.findAllByOwner(owner)).thenReturn(Collections.emptyList());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> cardService.findAllByOwner(owner));
        assertEquals(CARD_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findAllByOwner(owner);
    }
}
