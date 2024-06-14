package com.aston.rapidride.entity;

import jakarta.persistence.*;
import lombok.*;
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
    @NonNull
    Long carId;
    Long userId;
    @NonNull
    LocalDate date;
    @Column(name = "summ")
    BigDecimal sum;
    @NonNull
    String registration_number;
    @NonNull
    Long payment_id;
}
