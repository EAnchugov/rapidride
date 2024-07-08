package com.aston.rapidride.dto.response;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private Long id;

    private String vin;

    private Long power;

    private int year;

    private String registrationNumber;

    private int price;

    private String photo;

    private String engineTypeId;

    private String statusId;

    private String brandId;

    private String modelId;

    private String colorId;
}
