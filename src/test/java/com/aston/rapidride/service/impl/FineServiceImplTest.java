package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.request.FineRequest;
import com.aston.rapidride.entity.Car;
import com.aston.rapidride.entity.Fine;
import com.aston.rapidride.entity.User;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.CarRepository;
import com.aston.rapidride.repository.FineRepository;
import com.aston.rapidride.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FineServiceImplTest {
    FineRequest fineRequest = FineRequest.builder()
            .id(1L)
            .carId(1L)
            .userId(1L)
            .date(LocalDate.now())
            .sum(BigDecimal.valueOf(1L))
            .registrationNumber("ABC123")
            .paymentId(1L)
            .build();

    @Mock
    private FineRepository fineRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FineServiceImpl fineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetById() {
        Fine fine = new Fine();
        fine.setId(1L);
        when(fineRepository.findById(1L)).thenReturn(Optional.of(fine));

        Fine result = fineService.getById(1L);
        assertEquals(fine, result);
    }

    @Test
    void testGetByIdNotFound() {
        when(fineRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> fineService.getById(1L));
    }

    @Test
    void testCreateFine() {
        FineRequest request = fineRequest;
        request.setId(1L);
        request.setCarId(1L);
        request.setUserId(1L);
        request.setDate(LocalDate.now());
        request.setSum(BigDecimal.valueOf(100));
        request.setRegistrationNumber("ABC123");
        request.setPaymentId(123L);

        Fine fine = new Fine();
        Car car = new Car();
        User user = new User();

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(fineRepository.save(any(Fine.class))).thenReturn(fine);

        fineService.createFine(request);

        verify(fineRepository).save(any(Fine.class));
    }

    @Test
    void testCreateFineCarNotFound() {
        FineRequest request = fineRequest;
        request.setId(1L);
        request.setCarId(1L);
        request.setUserId(1L);
        request.setDate(LocalDate.now());
        request.setSum(BigDecimal.valueOf(100));
        request.setRegistrationNumber("ABC123");
        request.setPaymentId(123L);

        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> fineService.createFine(request));
    }

    @Test
    void testCreateFineUserNotFound() {
        FineRequest request = fineRequest;
        request.setId(1L);
        request.setCarId(1L);
        request.setUserId(1L);
        request.setDate(LocalDate.now());
        request.setSum(BigDecimal.valueOf(100));
        request.setRegistrationNumber("ABC123");
        request.setPaymentId(123L);

        when(carRepository.findById(1L)).thenReturn(Optional.of(new Car()));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> fineService.createFine(request));
    }

    @Test
    void testUpdateFine() {
        FineRequest request = fineRequest;
        request.setId(1L);
        request.setCarId(1L);
        request.setUserId(1L);
        request.setDate(LocalDate.now());
        request.setSum(BigDecimal.valueOf(100));
        request.setRegistrationNumber("ABC123");
        request.setPaymentId(123L);

        Fine fine = new Fine();
        Car car = new Car();
        User user = new User();

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(fineRepository.save(any(Fine.class))).thenReturn(fine);

        fineService.updateFine(request);

        verify(fineRepository).save(any(Fine.class));
    }

    @Test
    void testGetAllFine() {
        List<Fine> fines = new ArrayList<>();
        when(fineRepository.findAll()).thenReturn(fines);

        List<Fine> result = fineService.getAllFine();
        assertEquals(fines, result);
    }

    @Test
    void testGetAllFinesByUserId() {
        List<Fine> fines = new ArrayList<>();
        when(fineRepository.findAllByUserId(1L)).thenReturn(fines);

        List<Fine> result = fineService.getAllFinesByUserId(1L);
        assertEquals(fines, result);
    }

    @Test
    void testGetAllFinesByCarId() {
        List<Fine> fines = new ArrayList<>();
        when(fineRepository.findAllByCarId(1L)).thenReturn(fines);

        List<Fine> result = fineService.getAllFinesByCarId(1L);
        assertEquals(fines, result);
    }

    @Test
    void testGetAllFinesByUserIdAndCarId() {
        List<Fine> fines = new ArrayList<>();
        when(fineRepository.findAllByCarIdAndUserId(1L, 1L)).thenReturn(fines);

        List<Fine> result = fineService.getAllFinesByUserIdAndCarId(1L, 1L);
        assertEquals(fines, result);
    }

    @Test
    void testGetFinesByRegistrationNumber() {
        List<Fine> fines = new ArrayList<>();
        when(fineRepository.findAllByRegistrationNumber(1L)).thenReturn(fines);

        List<Fine> result = fineService.getFinesByRegistrationNumber(1L);
        assertEquals(fines, result);
    }

    @Test
    void testGetAllFinesBySum() {
        List<Fine> fines = new ArrayList<>();
        when(fineRepository.findAllBySum(BigDecimal.valueOf(100))).thenReturn(fines);

        List<Fine> result = fineService.getAllFinesBySum(BigDecimal.valueOf(100));
        assertEquals(fines, result);
    }

    @Test
    void testGetAllByDate() {
        List<Fine> fines = new ArrayList<>();
        when(fineRepository.findAllByDate(LocalDate.now())).thenReturn(fines);

        List<Fine> result = fineService.getAllByDate(LocalDate.now());
        assertEquals(fines, result);
    }
}
