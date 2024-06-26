package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.CardMapper;
import com.aston.rapidride.dto.mapper.PaymentMapper;
import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.request.PaymentRequest;
import com.aston.rapidride.dto.response.PaymentResponse;
import com.aston.rapidride.entity.Payment;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.PaymentRepository;
import com.aston.rapidride.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.aston.rapidride.utility.TextConstants.PAYMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {


    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final CardMapper cardMapper;

    @Override
    public void createPayment(PaymentRequest paymentRequest) {
        paymentRepository.save(paymentMapper.mapToPayment(paymentRequest));
    }

    @Override
    public void updatePayment(Long id, PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PAYMENT_NOT_FOUND.get()));
        payment.setFromSender(paymentRequest.getFromSender());
        payment.setToGetter(paymentRequest.getToGetter());
        payment.setPaymentSumm(paymentRequest.getPaymentSumm());
        //payment.setTransactionDateTime(paymentRequest.getTransactionDateTime());
        paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentResponse getById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PAYMENT_NOT_FOUND.get()));
        return paymentMapper.mapToResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayment() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(paymentMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponse> getAllPaymentBySumm(BigDecimal sum) {
        List<Payment> payments = paymentRepository.findByPaymentSumm(sum);

        return payments.stream().map(paymentMapper::mapToResponse).toList();
    }

    @Override
    public List<PaymentResponse> getAllPaymentByFromSenderCard(CardRequest cardRequest) {
        List<Payment> payments = paymentRepository.findByFromSender(cardMapper.mapRequestToEntity(cardRequest));

        return payments.stream().map(paymentMapper::mapToResponse).toList();
    }

    @Override
    public List<PaymentResponse> getAllPaymentByFromGetterCard(CardRequest cardRequest) {
        List<Payment> payments = paymentRepository.findByToGetter(cardMapper.mapRequestToEntity(cardRequest));

        return payments.stream().map(paymentMapper::mapToResponse).toList();
    }

}
