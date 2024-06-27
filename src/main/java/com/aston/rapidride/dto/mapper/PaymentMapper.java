package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.PaymentRequest;
import com.aston.rapidride.dto.response.PaymentResponse;
import com.aston.rapidride.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponse mapToResponse(Payment payment) {
        if (payment == null) {
            return null;
        }
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(payment.getId());
        paymentResponse.setFromSender(payment.getFromSender());
        paymentResponse.setToGetter(payment.getToGetter());
        paymentResponse.setPaymentSumm(payment.getPaymentSumm());
        paymentResponse.setTransactionDateTime(payment.getTransactionDateTime());

        return paymentResponse;
    }

    public Payment mapToPayment(PaymentRequest paymentRequest) {
        if (paymentRequest == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setFromSender(paymentRequest.getFromSender());
        payment.setToGetter(paymentRequest.getToGetter());
        payment.setPaymentSumm(paymentRequest.getPaymentSumm());
        payment.setTransactionDateTime(paymentRequest.getTransactionDateTime());

        return payment;
    }
}
