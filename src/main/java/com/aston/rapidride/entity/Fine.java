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
    @Id
    Long id;
    //TODO Сделать Car классом после добавления
    @NotBlank
    Long carId;
    //TODO Сделать User классом после добавления
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
