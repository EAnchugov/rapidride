package com.aston.rapidride.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class DocumentTypeRequest {

    @NotBlank(message = "name can't be empty")
    private String name;
}
