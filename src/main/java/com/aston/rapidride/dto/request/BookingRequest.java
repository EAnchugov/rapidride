package com.aston.rapidride.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingRequest {

    private Long carId;

    private Long userId;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long paymentId;

    private Long statusId;

    private String comments;
}
