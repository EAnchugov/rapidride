package com.aston.rapidride.service;

import com.aston.rapidride.entity.Car;
import com.aston.rapidride.entity.Fine;
import com.aston.rapidride.entity.User;
import com.aston.rapidride.repository.FineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FineServiceImpl implements FineService {

    private FineRepository fineRepository;

    @Override
    public Fine getById(Long id) {
        return null;
    }

    @Override
    public void createFine(Fine fine) {

    }

    @Override
    public void updateFine(Fine fine) {

    }

    @Override
    public List<Fine> getAllFine() {
        return List.of();
    }

    @Override
    public List<Fine> getAllFineByUser(User user) {
        return List.of();
    }

    @Override
    public List<Fine> getAllFineByCar(Car car) {
        return List.of();
    }

    @Override
    public Fine getFineByUserAndCar(User user, Car car) {
        return null;
    }

    @Override
    public Fine getFineByRegistrationNumber(Long registrationNumber) {
        return null;
    }

    @Override
    public List<Fine> getAllFinesBySum(BigDecimal sum) {
        return List.of();
    }

    @Override
    public List<Fine> getAllByDate(LocalDate date) {
        return List.of();
    }
}
