package com.aston.rapidride.dto.response;

import com.aston.rapidride.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CardResponse {

    private Long id;

    private Long number;

    @NotBlank(message = "owner can't be empty")
    private String owner;

    @NotBlank(message = "expire date can't be empty")
    private String expireDate;

    @NotNull(message = "user can't be empty")
    private User user;
}
