package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.CardMapper;
import com.aston.rapidride.dto.mapper.PaymentMapper;
import com.aston.rapidride.dto.request.CarRequest;
import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.request.PaymentRequest;
import com.aston.rapidride.dto.response.PaymentResponse;
import com.aston.rapidride.entity.Card;
import com.aston.rapidride.entity.Payment;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.BookingRepository;
import com.aston.rapidride.repository.CardRepository;
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


    private final PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;
    private final CardMapper cardMapper;

    @Override
    public void createPayment (PaymentRequest paymentRequest){
        paymentRepository.save(paymentMapper.mapToPayment(paymentRequest));
    };

    @Override
    public void updatePayment (PaymentRequest paymentRequest){
        Payment payment = paymentRepository.findById(paymentRequest.getId())
                .orElseThrow(() -> new NotFoundException("Payment not found."));
        payment.setId(paymentRequest.getId());
        payment.setFromSender(paymentRequest.getFromSender());
        payment.setFromSender(paymentRequest.getFromSender());
        payment.setPaymentSumm(paymentRequest.getPaymentSumm());
        payment.setTransactionDateTime(paymentRequest.getTransactionDateTime());
        paymentRepository.save(payment);
    };

    @Override
    public void deletePayment (Long id){
        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentResponse getById(Long id){
        PaymentResponse paymentResponse = paymentMapper.mapToResponse(paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment not found.")));
        return paymentResponse;
    };

    @Override
    public List<PaymentResponse> getAllPayment (){
        List<Payment> payments = paymentRepository.findAll();

        return payments.stream().map(paymentMapper::mapToResponse).toList();
    };

    @Override
    public List<PaymentResponse> getAllPaymentBySumm(BigDecimal sum){
        List<Payment> payments = paymentRepository.findByPaymentSumm(sum);

        return payments.stream().map(paymentMapper::mapToResponse).toList();
    };

    @Override
    public List<PaymentResponse> getAllPaymentByFromSenderCard(CardRequest cardRequest){
        List<Payment> payments = paymentRepository.findByFromSender(cardMapper.mapRequestToEntity(cardRequest));

        return payments.stream().map(paymentMapper::mapToResponse).toList();
    };

    @Override
    public List<PaymentResponse> getAllPaymentByFromGetterCard(CardRequest cardRequest){
        List<Payment> payments = paymentRepository.findByToGetter(cardMapper.mapRequestToEntity(cardRequest));

        return payments.stream().map(paymentMapper::mapToResponse).toList();
    };

}
