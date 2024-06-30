package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.CarMapper;
import com.aston.rapidride.dto.request.CarRequest;
import com.aston.rapidride.dto.response.CarResponse;
import com.aston.rapidride.entity.*;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.*;
import com.aston.rapidride.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.aston.rapidride.utility.TextConstants.*;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    private final ColorRepository colorRepository;

    private final BrandRepository brandRepository;

    private final ModelRepository modelRepository;

    private final EngineTypeRepository engineTypeRepository;

    private final CarStatusRepository carStatusRepository;

    @Override
    public void create(CarRequest carRequest) {
        Car car = carMapper.mapToEntity(carRequest);

        Brand brand = brandRepository.findById(carRequest.getBrandId())
                .orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.get()));
        car.setBrand(brand);

        Color color = colorRepository.findById(carRequest.getColorId())
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.get()));
        car.setColor(color);

        Model model = modelRepository.findById(carRequest.getModelId())
                .orElseThrow(() -> new NotFoundException(MODEL_NOT_FOUND.get()));
        car.setModel(model);

        EngineType engineType = engineTypeRepository.findById(carRequest.getEngineTypeId())
                .orElseThrow(() -> new NotFoundException(ENGINE_TYPE_NOT_FOUND.get()));
        car.setEngineType(engineType);

        CarStatus carStatus = carStatusRepository.findById(carRequest.getStatusId())
                .orElseThrow(() -> new NotFoundException(CAR_STATUS_NOT_FOUND.get()));
        car.setStatus(carStatus);
        carRepository.save(car);
    }

    @Override
    public CarResponse update(Long id, CarRequest carRequest) {
        Car carDB = carRepository.findById(id).orElseThrow(() -> new NotFoundException(CAR_NOT_FOUND.get()));

        carDB.setVin(carRequest.getVin());
        carDB.setPower(carRequest.getPower());
        carDB.setPrice(carRequest.getPrice());
        carDB.setRegistrationNumber(carRequest.getRegistrationNumber());
        carDB.setYear(carRequest.getYear());
        carDB.setPhoto(carRequest.getPhoto());

        Brand brand = brandRepository.findById(carRequest.getBrandId()).orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.get()));
        carDB.setBrand(brand);

        Color color = colorRepository.findById(carRequest.getColorId()).orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.get()));
        carDB.setColor(color);

        Model model = modelRepository.findById(carRequest.getModelId()).orElseThrow(() -> new NotFoundException(MODEL_NOT_FOUND.get()));
        carDB.setModel(model);

        EngineType engineType = engineTypeRepository.findById(carRequest.getEngineTypeId()).orElseThrow(() -> new NotFoundException(ENGINE_TYPE_NOT_FOUND.get()));
        carDB.setEngineType(engineType);

        CarStatus carStatus = carStatusRepository.findById(carRequest.getStatusId()).orElseThrow(() -> new NotFoundException(CAR_STATUS_NOT_FOUND.get()));
        carDB.setStatus(carStatus);
        carRepository.save(carDB);

        return carMapper.mapToResponse(carDB);
    }

    @Override
    public CarResponse findById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CAR_NOT_FOUND.get()));
        return carMapper.mapToResponse(car);
    }

    @Override
    public List<CarResponse> findAll() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(carMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }


}
