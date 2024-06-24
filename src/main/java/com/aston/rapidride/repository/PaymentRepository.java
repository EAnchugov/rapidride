package com.aston.rapidride.repository;

import com.aston.rapidride.entity.Card;
import com.aston.rapidride.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentRepository extends JpaRepository <Payment, Long> {

    List<Payment> findByFromSender(Card card);

    List<Payment> findByToGetter(Card card);

    List<Payment> findByPaymentSumm(BigDecimal sum);
}
