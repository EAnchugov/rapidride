package com.aston.rapidride.service.impl;

import com.aston.rapidride.entity.Fine;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.FineRepository;
import com.aston.rapidride.service.FineService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.aston.rapidride.utility.TextConstants.FINE_NOT_FOUND;

@Service
@AllArgsConstructor
public class FineServiceImpl implements FineService {
    private final FineRepository repository;

    @Override
    @Transactional
    public Fine getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(FINE_NOT_FOUND.get()));
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
