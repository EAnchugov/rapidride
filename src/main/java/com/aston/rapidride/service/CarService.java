package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.CarRequest;
import com.aston.rapidride.dto.response.CarResponse;

import java.util.List;

public interface CarService {

    void create(CarRequest carRequest);

    CarResponse update(Long id, CarRequest carRequest);

    CarResponse findById(Long id);

    List<CarResponse> findAll();

    void deleteById(Long id);
}
