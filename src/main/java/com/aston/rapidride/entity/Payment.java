package com.aston.rapidride.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    @NotNull
    private Card fromSender;

    @ManyToOne()
    @JoinColumn(name = "to_getter")
    @NotNull
    private Card toGetter;

    @Column(name = "payment_summ")
    @NotBlank
    private BigDecimal paymentSumm;

    @Column(name = "transaction_datetime")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @NotBlank
    private LocalDateTime transactionDateTime = LocalDateTime.now();


}
