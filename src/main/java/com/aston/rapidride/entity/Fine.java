package com.aston.rapidride.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;
    //TODO Сделать Car классом после добавления
    @NotBlank
    private Long carId;
    //TODO Сделать User классом после добавления
    @NotBlank
    private Long userId;
    @NotBlank
    private LocalDate date;
    @Column(name = "summ")
    private BigDecimal sum;
    @NotBlank
    private String registration_number;
    @NotBlank
    private Long payment_id;
}
