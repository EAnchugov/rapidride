package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.request.PaymentRequest;
import com.aston.rapidride.dto.response.PaymentResponse;
import com.aston.rapidride.entity.Payment;
import com.aston.rapidride.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public List<PaymentResponse> getPayments() {
        return paymentService.getAllPayment();
    }
    @PostMapping
    public void createPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentService.createPayment(paymentRequest);
    }
    @PutMapping()
    public void updatePayment(@RequestBody PaymentRequest paymentRequest) {
        paymentService.updatePayment(paymentRequest);
    }
    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id) {
        return paymentService.getById(id);
    }
    @DeleteMapping("/{id}")
    public void deletePaymentById(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
    @GetMapping("/sum")
    public List<PaymentResponse> getPaymentSum(@RequestParam BigDecimal sum) {
        return paymentService.getAllPaymentBySumm(sum);
    }
    @GetMapping("/sender")
    public List<PaymentResponse> getPaymentSender(@RequestParam CardRequest cardRequest) {
        return paymentService.getAllPaymentByFromSenderCard(cardRequest);
    }
    @GetMapping("/getter")
    public List<PaymentResponse> getPaymentGetter(@RequestParam CardRequest cardRequest) {
        return paymentService.getAllPaymentByFromGetterCard(cardRequest);
    }


}
