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
    //TODO Сделать Car классом после добавления
    @NotBlank
    private Long carId;
    //TODO Сделать User классом после добавления
    @NotBlank
    private Long userId;
    @NotBlank
    @Column(columnDefinition = "DATE")
    private LocalDate date;
    @Column(name = "summ")
    private BigDecimal sum;
    @NotBlank
    private String registrationNumber;
    @NotBlank
    private Long paymentId;
}
