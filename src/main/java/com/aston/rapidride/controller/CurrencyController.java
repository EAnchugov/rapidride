package com.aston.rapidride.controller;

import com.aston.rapidride.dto.response.CurrencyDto;
import com.aston.rapidride.service.impl.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Azimov Ruslan
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/currencies")
public class CurrencyController {

    private final CurrencyService service;

    @GetMapping
    public CurrencyDto[] getAllCurrencies() {
        return service.getAllCurrencies();
    }
}
