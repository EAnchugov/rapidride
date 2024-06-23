package com.aston.rapidride.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BookingStatusResponse {

    private Long id;

    @NotBlank
    private String name;
}
