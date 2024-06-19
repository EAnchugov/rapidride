package com.aston.rapidride.dto.user;
import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private Long zipcode;
    private String city;
    private String street;
    private String house;
    private String apartment;
}
