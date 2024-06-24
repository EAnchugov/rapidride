package com.aston.rapidride.dto.response;

import com.aston.rapidride.dto.request.AddressRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private AddressResponse addressResponse;
}
