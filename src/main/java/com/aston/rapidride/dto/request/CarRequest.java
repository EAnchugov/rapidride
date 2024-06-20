package com.aston.rapidride.dto.request;

import lombok.Data;

@Data
public class CarRequest {

    private String vin;

    private Long power;

    private int year;

    private String registrationNumber;

    private int price;

    private String photo;


    private Long brandId;

    private Long modelId;

    private Long colorId;

    private Long statusId;

    private Long engineTypeId;
}
