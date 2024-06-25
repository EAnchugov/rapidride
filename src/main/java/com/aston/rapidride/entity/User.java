package com.aston.rapidride.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "name can't be empty")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "name can't be empty")
    private String lastName;

    @Column(name = "phone", nullable = false)
    @NotBlank(message = "name can't be empty")
    private String phone;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "name can't be empty")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "name can't be empty")
    private String password;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @OneToMany
    private List<Car> userFavorites;

    @ManyToMany
    private List<Role> roles;

    @OneToMany
    private  List<UserDocument> userDocuments;
}
