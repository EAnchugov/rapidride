package com.aston.rapidride.dto.filter;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CardByOwnerFilter {

    @NotBlank(message = "owner can't be empty")
    private String owner;
}
