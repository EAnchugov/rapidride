package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.FineRequest;
import com.aston.rapidride.entity.Fine;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FineService {
    Fine getById(Long id);

    void createFine(FineRequest request);

    void updateFine(FineRequest request);

    List<Fine> getAllFine();

    List<Fine> getAllFinesByUserId(Long userId);

    List<Fine> getAllFinesByCarId(Long carId);

    List<Fine> getAllFinesByUserIdAndCarId(Long userId, Long carId);

    List<Fine> getFinesByRegistrationNumber(Long registrationNumber);

    List<Fine> getAllFinesBySum(BigDecimal sum);

    List<Fine> getAllByDate(LocalDate date);

}
