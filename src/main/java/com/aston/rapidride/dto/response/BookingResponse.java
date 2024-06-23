package com.aston.rapidride.dto.response;

import com.aston.rapidride.entity.BookingStatus;
import com.aston.rapidride.entity.Car;
import com.aston.rapidride.entity.Payment;
import com.aston.rapidride.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookingResponse {

    private Long id;

    @NotBlank
    private Car car;

    @NotBlank
    private User user;

    @NotBlank
    private LocalDate startDate;

    @NotBlank
    private LocalDate endDate;

    @NotBlank
    private BigDecimal totalAmount;

    @NotBlank
    private Payment payment;

    @NotBlank
    private BookingStatus status;

    private String comments;
}
