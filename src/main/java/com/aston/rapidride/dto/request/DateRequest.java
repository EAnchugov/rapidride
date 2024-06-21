package com.aston.rapidride.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DateRequest {

    LocalDate startDate;

    LocalDate endDate;
}
