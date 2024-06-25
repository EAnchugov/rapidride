package com.aston.rapidride.dto.request;

import com.aston.rapidride.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CardRequest {

    private Long number;

    @NotBlank(message = "owner can't be empty")
    private String owner;

    @NotBlank(message = "expire date can't be empty")
    private String expireDate;

    //    @NotNull(message = "user can't be empty")
    private User user;
}
