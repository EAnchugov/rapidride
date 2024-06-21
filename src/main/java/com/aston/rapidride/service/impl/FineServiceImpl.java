package com.aston.rapidride.service.impl;

import com.aston.rapidride.entity.Car;
import com.aston.rapidride.entity.Fine;
import com.aston.rapidride.entity.User;
import com.aston.rapidride.repository.FineRepository;
import com.aston.rapidride.service.FineService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FineServiceImpl implements FineService {
    private final FineRepository repository;

    @Override
    @Transactional
    public Fine getById(Long id) {
        return repository.findById(id).orElse(new Fine());
    }

    @Override
    @Transactional

    public void createFine(Fine fine) {
        repository.save(fine);
    }

    @Override
    @Transactional
    public void updateFine(Fine fine) {
        repository.save(fine);
    }

    @Override
    @Transactional
    public List<Fine> getAllFine() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public List<Fine> getAllFineByUser(User user) {
        return repository.findAllByUserId(user.getId());
    }

    @Override
    @Transactional
    public List<Fine> getAllFineByCar(Car car) {
        return repository.findAllByCar(car);
    }

    @Override
    @Transactional
    public Fine getFineByUserAndCar(User user, Car car) {
        return repository.findAllByCarIdAndUserId(car.getId(), user.getId());
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
