package com.aston.rapidride.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class FineRequest {
        private Long id;
        private Long carId;
        private Long userId;
        private LocalDate date;
        private BigDecimal sum;
        private String registrationNumber;
        private Long paymentId;
}
