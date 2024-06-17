package com.aston.rapidride.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "fines")
public class Fine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private  User user;
    @NotBlank
    @Column(columnDefinition = "DATE")
    private LocalDate date;
    @Column(name = "summ", columnDefinition = "DECIMAL")
    private BigDecimal sum;
    @NotBlank
    @Column(name = "registration_number")
    private String registrationNumber;
    @NotBlank
    @Column(name = "payment_id")
    private Long paymentId;
}
