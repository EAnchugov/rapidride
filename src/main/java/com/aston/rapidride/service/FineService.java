package com.aston.rapidride.service;

import com.aston.rapidride.entity.Car;
import com.aston.rapidride.entity.Fine;
import com.aston.rapidride.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FineService {
    Fine getById(Long id);

    void createFine(Fine fine);

    void updateFine(Fine fine);

    List<Fine> getAllFine();

    List<Fine> getAllFineByUser(User user);

    List<Fine> getAllFineByCar(Car car);

    Fine getFineByUserAndCar(User user, Car car);

    Fine getFineByRegistrationNumber(Long registrationNumber);

    List<Fine> getAllFinesBySum(BigDecimal sum);

    List<Fine> getAllByDate(LocalDate date);

}
