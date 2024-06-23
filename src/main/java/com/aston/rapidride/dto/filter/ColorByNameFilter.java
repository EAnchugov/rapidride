package com.aston.rapidride.dto.filter;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ColorByNameFilter {

    @NotBlank(message = "name can't be empty")
    private String name;
}