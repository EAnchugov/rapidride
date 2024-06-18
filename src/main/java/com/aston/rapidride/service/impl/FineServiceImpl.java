package com.aston.rapidride.service.impl;

import com.aston.rapidride.entity.Car;
import com.aston.rapidride.entity.Fine;
import com.aston.rapidride.entity.User;
import com.aston.rapidride.repository.FineRepository;
import com.aston.rapidride.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FineServiceImpl implements FineService {
    private final FineRepository repository;

    @Autowired
    public FineServiceImpl(FineRepository repository) {
        this.repository = repository;
    }

    @Override
    public Fine getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void createFine(Fine fine) {
        repository.save(fine);
    }

    @Override
    public void updateFine(Fine fine) {
        repository.save(fine);
    }

    @Override
    public List<Fine> getAllFine() {
        return repository.findAll();
    }

    @Override
    public List<Fine> getAllFineByUser(User user) {
        return repository.findAllByUserId(user.getId());
    }

    @Override
    public List<Fine> getAllFineByCar(Car car) {
        return repository.findAllByCarId(car.getId());
    }

    @Override
    public Fine getFineByUserAndCar(User user, Car car) {
        return repository.findAllByCarIdAndUserId(car.getId(), user.getId());
    }

    @Override
    public List<Fine> getFinesByRegistrationNumber(Long registrationNumber) {
        return repository.findAllByRegistrationNumber(registrationNumber);
    }

    @Override
    public List<Fine> getAllFinesBySum(BigDecimal sum) {
        return repository.findAllBySum(sum);
    }

    @Override
    public List<Fine> getAllByDate(LocalDate date) {
        return repository.findAllByDate(date);
    }
}
