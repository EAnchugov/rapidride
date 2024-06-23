package com.aston.rapidride.service;

import com.aston.rapidride.entity.Card;
import com.aston.rapidride.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    Payment getById(Long id);

    void createPayment (Payment payment);

    void updatePayment (Payment payment);

    List<Payment> getAllPayment ();

    List<Payment> getAllPaymentBySumm(BigDecimal sum);

    List<Payment> getAllPaymentByFromCard(Card fromCard);

    List<Payment> getAllPaymentAfterByTransactionDate(LocalDateTime transactionDate);

    List<Payment> getAllPaymentBeforeByTransactionDate(LocalDateTime transactionDate);

    List<Payment> getAllPaymentBetweenByTransactionDate(LocalDateTime transactionDate1, LocalDateTime transactionDate2);


}
