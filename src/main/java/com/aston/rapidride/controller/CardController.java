package com.aston.rapidride.controller;

import com.aston.rapidride.dto.filter.CardByNumberFilter;
import com.aston.rapidride.dto.filter.CardByOwnerFilter;
import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.response.CardResponse;
import com.aston.rapidride.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CardResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CardResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody CardRequest request) {
        service.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id, @Valid @RequestBody CardRequest request) {
        service.update(id, request);
    }

    @PostMapping("/number")
    @ResponseStatus(HttpStatus.OK)
    public CardResponse getByNumber(
            @Valid @RequestBody CardByNumberFilter filter) {
        return service.getByNumber(filter.getNumber());
    }

    @PostMapping("/owner")
    @ResponseStatus(HttpStatus.OK)
    public List<CardResponse> findAllByOwner(
            @Valid @RequestBody CardByOwnerFilter filter) {
        return service.findAllByOwner(filter.getOwner());
    }
}
