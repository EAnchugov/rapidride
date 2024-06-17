package com.aston.rapidride.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "from_sender")
    private Card fromSender;

    @ManyToOne()
    @JoinColumn(name = "to_getter")
    private Card toGetter;

    @Column(name = "payment_summ")
    private BigDecimal paymentSumm;

    @Column(name = "transaction_datetime")
    private LocalDateTime transactionDateTime;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Fine> fines;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Booking> bookings;

}
