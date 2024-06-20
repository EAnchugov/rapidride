package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.CarMapper;
import com.aston.rapidride.dto.request.CarRequest;
import com.aston.rapidride.dto.response.CarResponse;
import com.aston.rapidride.entity.*;
import com.aston.rapidride.repository.*;
import com.aston.rapidride.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(()-> new RuntimeException("Brand with id " + carRequest.getBrandId() + " not found"));
        car.setBrand(brand);

        Color color = colorRepository.findById(carRequest.getColorId())
                .orElseThrow(()-> new RuntimeException("Color with id " + carRequest.getColorId() + " not found"));
        car.setColor(color);

        Model model = modelRepository.findById(carRequest.getModelId())
                .orElseThrow(()-> new RuntimeException("Model with id " + carRequest.getModelId() + " not found"));
        car.setModel(model);

        EngineType engineType = engineTypeRepository.findById(carRequest.getEngineTypeId())
                .orElseThrow(()-> new RuntimeException("EngineType with id " + carRequest.getEngineTypeId() + " not found"));
        car.setEngineType(engineType);

        CarStatus carStatus = carStatusRepository.findById(carRequest.getStatusId())
                .orElseThrow(()-> new RuntimeException("Status with id " + carRequest.getStatusId() + " not found"));
        car.setStatus(carStatus);
        carRepository.save(car);
    }

    @Override
    public CarResponse update(Long id, CarRequest carRequest) {
        Car carDB = carRepository.findById(id).orElseThrow(()-> new RuntimeException("Car with id " + id + " not found"));

        carDB.setVin(carRequest.getVin());
        carDB.setPower(carRequest.getPower());
        carDB.setPrice(carRequest.getPrice());
        carDB.setRegistrationNumber(carRequest.getRegistrationNumber());
        carDB.setYear(carRequest.getYear());
        carDB.setPhoto(carRequest.getPhoto());

        Brand brand = brandRepository.findById(carRequest.getBrandId())
                .orElseThrow(()-> new RuntimeException("Brand with id " + carRequest.getBrandId() + " not found"));
        carDB.setBrand(brand);

        Color color = colorRepository.findById(carRequest.getColorId())
                .orElseThrow(()-> new RuntimeException("Color with id " + carRequest.getColorId() + " not found"));
        carDB.setColor(color);

        Model model = modelRepository.findById(carRequest.getModelId())
                .orElseThrow(()-> new RuntimeException("Model with id " + carRequest.getModelId() + " not found"));
        carDB.setModel(model);

        EngineType engineType = engineTypeRepository.findById(carRequest.getEngineTypeId())
                .orElseThrow(()-> new RuntimeException("EngineType with id " + carRequest.getEngineTypeId() + " not found"));
        carDB.setEngineType(engineType);

        CarStatus carStatus = carStatusRepository.findById(carRequest.getStatusId())
                .orElseThrow(()-> new RuntimeException("Status with id " + carRequest.getStatusId() + " not found"));
        carDB.setStatus(carStatus);
        carRepository.save(carDB);

        return carMapper.mapToResponse(carDB);
    }

    @Override
    public CarResponse findById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Car with id " + id + " not found"));
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
