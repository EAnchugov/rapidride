package com.aston.rapidride.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "First Name должен быть заполнен")

    private String firstName;

    @NotBlank(message = "Last name должен быть заполнен")
    private String lastName;

    @NotBlank(message = "Email должен быть заполнен")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Enter your password")
    private String password;

    private String repeatPassword;

    @NotBlank(message = "Phone number должен быть заполнен")
    private String phone;

}