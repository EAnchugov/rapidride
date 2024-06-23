package com.aston.rapidride.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class BookingRequest {

    @NotBlank
    private Long carId;

    @NotBlank
    private Long userId;

    @NotBlank
    private LocalDate startDate;

    @NotBlank
    private LocalDate endDate;

    @NotBlank
    private Long paymentId;

    @NotBlank
    private Long statusId;

    private String comments;
}
