package com.aston.rapidride.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class FineResponse {
    private Long id;
    private CarResponse car;

    //TODO Сделать UserResponce
    private Long userId;
    private LocalDate date;
    private BigDecimal sum;
    private String registrationNumber;
    private Long paymentId;
}
