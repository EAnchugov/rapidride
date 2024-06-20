package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.CarStatusRequest;
import com.aston.rapidride.dto.response.CarStatusResponse;

import java.util.List;

public interface CarStatusService {

    void create(CarStatusRequest carStatusRequest);

    CarStatusResponse update(Long id, CarStatusRequest carStatusRequest);

    CarStatusResponse findById(Long id);

    List<CarStatusResponse> findAll();

    void deleteById(Long id);
}
