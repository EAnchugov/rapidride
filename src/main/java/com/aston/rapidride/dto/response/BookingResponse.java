package com.aston.rapidride.dto.response;

import com.aston.rapidride.entity.BookingStatus;
import com.aston.rapidride.entity.Car;
import com.aston.rapidride.entity.Payment;
import com.aston.rapidride.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookingResponse {

    private Long id;

    private Car car;

    private User user;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal totalAmount;

    private Payment payment;

    private BookingStatus status;

    private String comments;
}
