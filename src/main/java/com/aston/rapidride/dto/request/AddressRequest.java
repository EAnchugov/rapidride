package com.aston.rapidride.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
    private Long zipcode;
    private String city;
    private String street;
    private String house;
    private String apartment;
}
