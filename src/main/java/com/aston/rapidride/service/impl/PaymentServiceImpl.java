package com.aston.rapidride.service.impl;

import com.aston.rapidride.entity.Card;
import com.aston.rapidride.entity.Payment;
import com.aston.rapidride.repository.PaymentRepository;
import com.aston.rapidride.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Override
    public Payment getById(Long id){
        return null;
    };

    public void createPayment (Payment payment){
    };

    public void updatePayment (Payment payment){
    };

    public List<Payment> getAllPayment (){
        return List.of();
    };

    public List<Payment> getAllPaymentBySumm(BigDecimal sum){
        return List.of();
    };

    public List<Payment> getAllPaymentByFromCard(Card fromCard){
        return List.of();
    };

    public List<Payment> getAllPaymentAfterByTransactionDate(LocalDateTime transactionDate){
        return List.of();
    };

    public List<Payment> getAllPaymentBeforeByTransactionDate(LocalDateTime transactionDate){
        return List.of();
    };

    public List<Payment> getAllPaymentBetweenByTransactionDate(LocalDateTime transactionDate1, LocalDateTime transactionDate2){
        return List.of();
    };

}
