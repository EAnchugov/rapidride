package com.aston.rapidride.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ModelResponse {

    private Long id;

    @NotBlank(message = "name can't be empty")
    private String name;
}
