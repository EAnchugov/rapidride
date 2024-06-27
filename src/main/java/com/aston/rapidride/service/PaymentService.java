package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.request.PaymentRequest;
import com.aston.rapidride.dto.response.PaymentResponse;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    PaymentResponse getById(Long id);

    void createPayment(PaymentRequest paymentRequest);

    void updatePayment(Long id, PaymentRequest paymentRequest);

    void deletePayment(Long id);

    List<PaymentResponse> getAllPayment();

    List<PaymentResponse> getAllPaymentBySumm(BigDecimal sum);

    List<PaymentResponse> getAllPaymentByFromSenderCard(CardRequest cardRequest);

    List<PaymentResponse> getAllPaymentByFromGetterCard(CardRequest cardRequest);

}
