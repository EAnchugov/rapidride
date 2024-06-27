package com.aston.rapidride.dto.response;

import com.aston.rapidride.entity.Card;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentResponse {
    private Long id;

    @NotNull
    private Card fromSender;

    @NotNull
    private Card toGetter;

    @NotBlank
    private BigDecimal paymentSumm;

    @NotBlank
    private LocalDateTime transactionDateTime;
}
