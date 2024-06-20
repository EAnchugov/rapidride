package com.aston.rapidride.dto.user;
import com.aston.rapidride.entity.Address;
import com.aston.rapidride.entity.Car;
import com.aston.rapidride.entity.Role;
import com.aston.rapidride.entity.UserDocument;
import lombok.*;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Address address;
    private Boolean isActive;
    private List<Car> userFavorites;
    private List<Role> roles;
    private List<UserDocument> userDocuments;
}
