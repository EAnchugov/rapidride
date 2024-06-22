package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.request.PaymentRequest;
import com.aston.rapidride.dto.response.PaymentResponse;
import com.aston.rapidride.entity.Card;
import com.aston.rapidride.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    PaymentResponse getById(Long id);

    void createPayment (PaymentRequest paymentRequest);

    void updatePayment (PaymentRequest paymentRequest);

    void deletePayment (Long id);

    List<PaymentResponse> getAllPayment ();

    List<PaymentResponse> getAllPaymentBySumm(BigDecimal sum);

    List<PaymentResponse> getAllPaymentByFromSenderCard(CardRequest cardRequest);

    List<PaymentResponse> getAllPaymentByFromGetterCard(CardRequest cardRequest);

    }
