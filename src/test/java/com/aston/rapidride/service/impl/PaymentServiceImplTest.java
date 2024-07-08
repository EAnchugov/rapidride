package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.CardMapper;
import com.aston.rapidride.dto.mapper.PaymentMapper;
import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.request.PaymentRequest;
import com.aston.rapidride.dto.response.PaymentResponse;
import com.aston.rapidride.entity.Card;
import com.aston.rapidride.entity.Payment;
import com.aston.rapidride.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private CardMapper cardMapper;
    @Mock
    private PaymentMapper paymentMapper;
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    public void createPaymentTest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        Card fromCard = new Card();
        Card toCard = new Card();
        BigDecimal sum = BigDecimal.valueOf(10000L);
        LocalDateTime dateTime = LocalDateTime.now();
        paymentRequest.setFromSender(fromCard);
        paymentRequest.setToGetter(toCard);
        paymentRequest.setTransactionDateTime(dateTime);
        paymentRequest.setPaymentSumm(sum);

        Payment payment = new Payment();
        payment.setFromSender(fromCard);
        payment.setToGetter(toCard);
        payment.setTransactionDateTime(dateTime);
        payment.setPaymentSumm(sum);
        when(paymentMapper.mapToPayment(paymentRequest)).thenReturn(payment);

        paymentService.createPayment(paymentRequest);

        verify(paymentMapper, times(1)).mapToPayment(paymentRequest);
        verify(paymentRepository, times(1)).save(payment);
    }


    @Test
    void updatePaymentTest() {
        Long id = 1L;
        PaymentRequest paymentRequest = new PaymentRequest();
        Card fromCard = new Card();
        Card toCard = new Card();
        BigDecimal sum = BigDecimal.valueOf(10000L);
        LocalDateTime dateTime = LocalDateTime.now();
        paymentRequest.setFromSender(fromCard);
        paymentRequest.setToGetter(toCard);
        paymentRequest.setTransactionDateTime(dateTime);
        paymentRequest.setPaymentSumm(sum);


        Payment payment = new Payment();
        payment.setId(id);
        payment.setFromSender(fromCard);
        payment.setToGetter(toCard);
        payment.setTransactionDateTime(dateTime);
        payment.setPaymentSumm(sum);

        when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));

        paymentService.updatePayment(id, paymentRequest);

        verify(paymentRepository, times(1)).findById(id);
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void deletePaymentTest() {
        Long id = 1L;
        doNothing().when(paymentRepository).deleteById(id);

        paymentService.deletePayment(id);

        verify(paymentRepository, times(1)).deleteById(id);
    }

    @Test
    public void getByIdTest() {
        Long id = 1L;
        Payment payment = new Payment();
        payment.setId(id);

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(id);

        when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));
        when(paymentMapper.mapToResponse(payment)).thenReturn(paymentResponse);

        PaymentResponse result = paymentService.getById(id);

        assertEquals(result, paymentResponse);
        verify(paymentRepository, times(1)).findById(id);
    }

    @Test
    public void getAllPaymentsTest() {
        Long id = 1L;
        Long id1 = 2L;
        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();
        payment.setId(id);
        payments.add(payment);

        List<PaymentResponse> paymentResponses = new ArrayList<>();
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(id);
        paymentResponses.add(paymentResponse);

        when(paymentMapper.mapToResponse(payment)).thenReturn(paymentResponse);
        when(paymentRepository.findAll()).thenReturn(payments);

        List<PaymentResponse> result = paymentService.getAllPayment();

        assertIterableEquals(result, paymentResponses);

        verify(paymentMapper, times(1)).mapToResponse(payment);
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    public void getAllPaymentBySummTest() {
        Long id = 1L;
        BigDecimal sum = BigDecimal.valueOf(10000L);
        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();
        payment.setId(id);
        payments.add(payment);

        List<PaymentResponse> paymentResponses = new ArrayList<>();
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(id);
        paymentResponses.add(paymentResponse);

        when(paymentRepository.findByPaymentSumm(sum)).thenReturn(payments);
        when(paymentMapper.mapToResponse(payment)).thenReturn(paymentResponse);

        List<PaymentResponse> result = paymentService.getAllPaymentBySumm(sum);

        assertIterableEquals(result, paymentResponses);

        verify(paymentRepository, times(1)).findByPaymentSumm(sum);
        verify(paymentMapper, times(1)).mapToResponse(payment);
    }

    @Test
    public void getAllPaymentByFromSenderCardTest() {
        Long id = 1L;
        Long number = 2345245365432098L;
        Card card = new Card();
        card.setId(id);
        card.setNumber(number);

        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();
        payment.setId(id);
        payment.setFromSender(card);
        payments.add(payment);

        List<PaymentResponse> paymentResponses = new ArrayList<>();
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(id);
        paymentResponse.setFromSender(card);
        paymentResponses.add(paymentResponse);

        CardRequest cardRequest = new CardRequest();
        cardRequest.setNumber(number);

        when(paymentRepository.findByFromSender(card)).thenReturn(payments);
        when(paymentMapper.mapToResponse(payment)).thenReturn(paymentResponse);
        when(cardMapper.mapRequestToEntity(cardRequest)).thenReturn(card);

        List<PaymentResponse> result = paymentService.getAllPaymentByFromSenderCard(cardRequest);

        assertIterableEquals(result, paymentResponses);

        verify(paymentRepository, times(1)).findByFromSender(card);
        verify(paymentMapper, times(1)).mapToResponse(payment);
        verify(cardMapper, times(1)).mapRequestToEntity(cardRequest);
    }

    @Test
    public void getAllPaymentByToGetterCardTest() {
        Long id = 1L;
        Long number = 2345245365432098L;
        Card card = new Card();
        card.setId(id);
        card.setNumber(number);

        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();
        payment.setId(id);
        payment.setToGetter(card);
        payments.add(payment);

        List<PaymentResponse> paymentResponses = new ArrayList<>();
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(id);
        paymentResponse.setToGetter(card);
        paymentResponses.add(paymentResponse);

        CardRequest cardRequest = new CardRequest();
        cardRequest.setNumber(number);

        when(paymentRepository.findByToGetter(card)).thenReturn(payments);
        when(paymentMapper.mapToResponse(payment)).thenReturn(paymentResponse);
        when(cardMapper.mapRequestToEntity(cardRequest)).thenReturn(card);

        List<PaymentResponse> result = paymentService.getAllPaymentByFromGetterCard(cardRequest);

        assertIterableEquals(result, paymentResponses);

        verify(paymentRepository, times(1)).findByToGetter(card);
        verify(paymentMapper, times(1)).mapToResponse(payment);
        verify(cardMapper, times(1)).mapRequestToEntity(cardRequest);
    }
}