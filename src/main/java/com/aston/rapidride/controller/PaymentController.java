package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.CardRequest;
import com.aston.rapidride.dto.request.PaymentRequest;
import com.aston.rapidride.dto.response.PaymentResponse;
import com.aston.rapidride.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getPayments() {
        return new ResponseEntity<>(paymentService.getAllPayment(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        paymentService.createPayment(paymentRequest);
        return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentRequest paymentRequest) {
        paymentService.updatePayment(id, paymentRequest);
        return new ResponseEntity<>("Successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentById(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return new ResponseEntity<>("Successfully", HttpStatus.OK);
    }

    @GetMapping("/sum")
    public ResponseEntity<List<PaymentResponse>> getPaymentSum(@RequestParam BigDecimal sum) {
        return new ResponseEntity<>(paymentService.getAllPaymentBySumm(sum), HttpStatus.OK);
    }

    @GetMapping("/sender")
    public ResponseEntity<List<PaymentResponse>> getPaymentSender(@RequestParam CardRequest cardRequest) {
        return new ResponseEntity<>(paymentService.getAllPaymentByFromSenderCard(cardRequest), HttpStatus.OK);
    }

    @GetMapping("/getter")
    public ResponseEntity<List<PaymentResponse>> getPaymentGetter(@RequestParam CardRequest cardRequest) {
        return new ResponseEntity<>(paymentService.getAllPaymentByFromGetterCard(cardRequest), HttpStatus.OK);
    }


}
