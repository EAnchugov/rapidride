package com.aston.rapidride.dto.request;

import com.aston.rapidride.entity.Card;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentRequest {
    private Long id;

    private Card fromSender;

    private Card toGetter;

    private BigDecimal paymentSumm;

    private LocalDateTime transactionDateTime;
}
