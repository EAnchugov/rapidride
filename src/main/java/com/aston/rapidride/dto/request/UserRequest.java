package com.aston.rapidride.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private AddressRequest addressRequest;
}
