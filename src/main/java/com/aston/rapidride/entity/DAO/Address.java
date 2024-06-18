package com.aston.rapidride.entity.DAO;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zipcode")
    private Long zipcode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house", nullable = false)
    private String house;

    @Column(name = "apartment", nullable = false)
    private String apartment;
}
