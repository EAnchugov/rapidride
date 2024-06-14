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
public class Fine {
    @Id
    Long id;
    @NotBlank
    Long carId;
    @NotBlank
    Long userId;
    @NotBlank
    LocalDate date;
    @Column(name = "summ")
    BigDecimal sum;
    @NotBlank
    String registration_number;
    @NotBlank
    Long payment_id;
}
