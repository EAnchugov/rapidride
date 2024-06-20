package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.CarRequest;
import com.aston.rapidride.dto.response.CarResponse;
import com.aston.rapidride.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarResponse mapToResponse(Car car) {
        if (car == null) {
            return null;
        }

        return CarResponse.builder()
                .id(car.getId())
                .vin(car.getVin())
                .power(car.getPower())
                .year(car.getYear())
                .registrationNumber(car.getRegistrationNumber())
                .price(car.getPrice())
                .photo(car.getPhoto())
                .colorId(car.getColor().getName())
                .statusId(car.getStatus().getName())
                .brandId(car.getBrand().getName())
                .modelId(car.getModel().getName())
                .engineTypeId(car.getEngineType().getName())
                .build();
    }

    public Car mapToEntity(CarRequest carRequest) {
        if (carRequest == null) {
            return null;
        }

        return Car.builder()
                .vin(carRequest.getVin())
                .power(carRequest.getPower())
                .year(carRequest.getYear())
                .registrationNumber(carRequest.getRegistrationNumber())
                .price(carRequest.getPrice())
                .photo(carRequest.getPhoto())
                .build();
    }
}
