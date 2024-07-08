package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.response.CurrencyDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Azimov Ruslan
 */
@Service
public class CurrencyService {

    private final RestTemplate restTemplate;

    public CurrencyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * Полутчение всех валют относительно BYN
     *
     * @return Массив с полученными валютами
     */
    public CurrencyDto[] getAllCurrencies() {
        String url = "https://api.nbrb.by/exrates/rates?periodicity=0";
        return this.restTemplate.getForObject(url, CurrencyDto[].class);
    }
}
