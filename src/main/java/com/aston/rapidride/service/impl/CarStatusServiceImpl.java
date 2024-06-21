package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.CarStatusMapper;
import com.aston.rapidride.dto.request.CarStatusRequest;
import com.aston.rapidride.dto.response.CarStatusResponse;
import com.aston.rapidride.entity.CarStatus;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.CarStatusRepository;
import com.aston.rapidride.service.CarStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CarStatusServiceImpl implements CarStatusService {

    private final CarStatusRepository carStatusRepository;

    private final CarStatusMapper mapper;

    @Override
    public void create(CarStatusRequest request) {
        CarStatus carStatus = mapper.mapToEntity(request);
        carStatusRepository.save(carStatus);
    }

    @Override
    public CarStatusResponse update(Long id, CarStatusRequest carStatusRequest) {
        CarStatus carStatusDB = carStatusRepository.findById(id).orElseThrow(()-> new NotFoundException("Car status not found"));
        carStatusDB.setName(carStatusRequest.getName());
        carStatusRepository.save(carStatusDB);
        return mapper.mapToResponse(carStatusDB);
    }

    @Override
    public CarStatusResponse findById(Long id) {
        CarStatus carStatus = carStatusRepository.findById(id).orElseThrow(()-> new NotFoundException("Car status not found"));
        return mapper.mapToResponse(carStatus);
    }

    @Override
    public List<CarStatusResponse> findAll() {
        List<CarStatus> statuses = carStatusRepository.findAll();

        return statuses.stream()
                .map(mapper::mapToResponse)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        carStatusRepository.deleteById(id);
    }
}
