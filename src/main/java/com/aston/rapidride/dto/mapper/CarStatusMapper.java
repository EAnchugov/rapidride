package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.CarStatusRequest;
import com.aston.rapidride.dto.response.CarStatusResponse;
import com.aston.rapidride.entity.CarStatus;
import org.springframework.stereotype.Component;

@Component
public class CarStatusMapper {

    public CarStatusResponse mapToResponse(CarStatus carStatus) {
        return CarStatusResponse.builder()
                .id(carStatus.getId())
                .name(carStatus.getName())
                .build();
    }

    public CarStatus mapToEntity(CarStatusRequest request) {
        return CarStatus.builder()
                .name(request.getName())
                .build();
    }
}
