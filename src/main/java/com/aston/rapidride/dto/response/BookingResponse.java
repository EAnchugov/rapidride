package com.aston.rapidride.dto.response;

import com.aston.rapidride.entity.BookingStatus;
import com.aston.rapidride.entity.Car;
import com.aston.rapidride.entity.Payment;
import com.aston.rapidride.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookingResponse {

    private Long id;

    @NotNull
    private Car car;

    @NotNull
    private User user;

    @NotBlank
    private LocalDate startDate;

    @NotBlank
    private LocalDate endDate;

    @NotBlank
    private BigDecimal totalAmount;

    @NotNull
    private Payment payment;

    @NotNull
    private BookingStatus status;

    private String comments;
}
