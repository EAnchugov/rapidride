package com.aston.rapidride.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {

    private Long id;

    @NotBlank(message = "name can't be empty")
    private String name;
}
