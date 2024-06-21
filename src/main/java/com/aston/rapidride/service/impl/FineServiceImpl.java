package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.FineMapper;
import com.aston.rapidride.dto.request.FineRequest;
import com.aston.rapidride.entity.Car;
import com.aston.rapidride.entity.Fine;
import com.aston.rapidride.entity.User;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.CarRepository;
import com.aston.rapidride.repository.FineRepository;
import com.aston.rapidride.repository.UserRepository;
import com.aston.rapidride.service.FineService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.aston.rapidride.utility.TextConstants.*;

@Service
@AllArgsConstructor
public class FineServiceImpl implements FineService {
    private final FineRepository repository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Fine getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(FINE_NOT_FOUND.get()));
    }

    @Override
    @Transactional
    public void createFine(FineRequest request) {
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new NotFoundException(CAR_NOT_FOUND.get()));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.get()));
        repository.save(FineMapper.toFine(request, user, car));
    }

    @Override
    @Transactional
    public void updateFine(FineRequest request) {
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new NotFoundException(CAR_NOT_FOUND.get()));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.get()));
        repository.save(FineMapper.toFine(request, user, car));
    }

    @Override
    @Transactional
    public List<Fine> getAllFine() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public List<Fine> getAllFinesByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public List<Fine> getAllFinesByCarId(Long carId) {
        return repository.findAllByCarId(carId);
    }

    @Override
    @Transactional
    public List<Fine> getAllFinesByUserIdAndCarId(Long userId, Long carId) {
        return repository.findAllByCarIdAndUserId(userId, carId);
    }

    @Override
    @Transactional
    public List<Fine> getFinesByRegistrationNumber(Long registrationNumber) {
        return repository.findAllByRegistrationNumber(registrationNumber);
    }

    @Override
    @Transactional
    public List<Fine> getAllFinesBySum(BigDecimal sum) {
        return repository.findAllBySum(sum);
    }

    @Override
    @Transactional
    public List<Fine> getAllByDate(LocalDate date) {
        return repository.findAllByDate(date);
    }
}
