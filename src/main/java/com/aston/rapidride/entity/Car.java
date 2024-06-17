package com.aston.rapidride.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vin")
    private String vin;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "power")
    private Long power;

    @Column(name = "engine_type_id")
    private Long engineTypeId;

    @Column(name = "year")
    private int year;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "color_id")
    private Long colorId;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "price")
    private int price;

    @Column(name = "photo")
    private String photo;
}
