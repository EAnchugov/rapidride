package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.AddressRequest;
import com.aston.rapidride.dto.request.UserRequest;
import com.aston.rapidride.dto.response.AddressResponse;
import com.aston.rapidride.dto.response.UserResponse;
import com.aston.rapidride.entity.Address;
import com.aston.rapidride.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse entityToResponse(User entity) {
        Address address = entity.getAddress();
        AddressResponse addressResponse = AddressResponse.builder()
                .zipcode(address.getZipcode())
                .city(address.getCity())
                .street(address.getStreet())
                .house(address.getHouse())
                .apartment(address.getApartment())
                .build();
        return UserResponse.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .addressResponse(addressResponse)
                .build();
    }

    public User requestToEntity(UserRequest userRequest) {
        AddressRequest addressRequest = userRequest.getAddressRequest();
        Address address = Address.builder()
                .zipcode(addressRequest.getZipcode())
                .city(addressRequest.getCity())
                .street(addressRequest.getStreet())
                .house(addressRequest.getHouse())
                .apartment(addressRequest.getApartment())
                .build();
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .phone(userRequest.getPhone())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .isActive(true)
                .address(address)
                .build();
    }
}
