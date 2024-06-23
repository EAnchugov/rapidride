package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.FineRequest;
import com.aston.rapidride.dto.response.CarResponse;
import com.aston.rapidride.dto.response.FineResponse;
import com.aston.rapidride.entity.Fine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FineMapper {

    public static Fine toFine(FineRequest request) {
        return Fine.builder()
                .id(request.getId())
                .paymentId(request.getPaymentId())
                //todo
                // спросить на занятии "Я бы связывание Car and User вынес бы на Service слой,
                // в текушем состоянии mapper как будто терят универасальность"
//                .car(car)
                .date(request.getDate())
                .sum(request.getSum())
//                .user(user)
                .registrationNumber(request.getRegistrationNumber())
                .build();
    }

    public static FineResponse toFineResponse(Fine fine) {
        return FineResponse.builder()
                .id(fine.getId())
                .car(CarResponse.builder()
                        .id(fine.getCar().getId())
                        .vin(fine.getCar().getVin())
                        .power(fine.getCar().getPower())
                        .year(fine.getCar().getYear())
                        .registrationNumber(fine.getCar().getRegistrationNumber())
                        .price(fine.getCar().getPrice())
                        .photo(fine.getCar().getPhoto())
                        .colorId(fine.getCar().getColor().getName())
                        .statusId(fine.getCar().getStatus().getName())
                        .brandId(fine.getCar().getBrand().getName())
                        .modelId(fine.getCar().getModel().getName())
                        .engineTypeId(fine.getCar().getEngineType().getName())
                        .build())
                .userId(fine.getUser().getId())
                .date(fine.getDate())
                .sum(fine.getSum())
                .registrationNumber(fine.getRegistrationNumber())
                .paymentId(fine.getPaymentId())
                .build();
    }
}
