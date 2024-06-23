package com.aston.rapidride.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class DateRequest {

    @NotBlank
    LocalDate startDate;

    @NotBlank
    LocalDate endDate;
}
