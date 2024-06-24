package com.aston.rapidride.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressResponse {
    private Long zipcode;
    private String city;
    private String street;
    private String house;
    private String apartment;
}
