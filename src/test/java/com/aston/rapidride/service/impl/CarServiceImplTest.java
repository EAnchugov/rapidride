package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.CarMapper;
import com.aston.rapidride.dto.request.CarRequest;
import com.aston.rapidride.entity.*;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {


    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @Mock
    private ColorRepository colorRepository;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private EngineTypeRepository engineTypeRepository;

    @Mock
    private CarStatusRepository carStatusRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private CarRequest carRequest;
    private Car car;

    @BeforeEach
    public void setUp() {
        carRequest = new CarRequest();
        carRequest.setVin("1234");
        carRequest.setPower(150L);
        carRequest.setYear(2020);
        carRequest.setRegistrationNumber("ABC123");
        carRequest.setPrice(10000);
        carRequest.setPhoto("photo.jpg");
        carRequest.setBrandId(1L);
        carRequest.setModelId(1L);
        carRequest.setColorId(1L);
        carRequest.setStatusId(1L);
        carRequest.setEngineTypeId(1L);

        car = new Car();
        car.setVin("1234");
        car.setPower(150L);
        car.setYear(2020);
        car.setRegistrationNumber("ABC123");
        car.setPrice(10000);
        car.setPhoto("photo.jpg");
    }

    @Test
    public void testCreateCar_Success() {
        when(carMapper.mapToEntity(carRequest)).thenReturn(car);
        when(brandRepository.findById(1L)).thenReturn(Optional.of(new Brand()));
        when(colorRepository.findById(1L)).thenReturn(Optional.of(new Color()));
        when(modelRepository.findById(1L)).thenReturn(Optional.of(new Model()));
        when(engineTypeRepository.findById(1L)).thenReturn(Optional.of(new EngineType()));
        when(carStatusRepository.findById(1L)).thenReturn(Optional.of(new CarStatus()));

        carService.create(carRequest);

        verify(carMapper).mapToEntity(carRequest);
        verify(brandRepository).findById(1L);
        verify(colorRepository).findById(1L);
        verify(modelRepository).findById(1L);
        verify(engineTypeRepository).findById(1L);
        verify(carStatusRepository).findById(1L);
        verify(carRepository).save(car);
    }

    @Test()
    public void testCreateCar_BrandNotFound() {
        when(carMapper.mapToEntity(carRequest)).thenReturn(car);
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()-> {
            carService.create(carRequest);
        });
    }

    @Test()
    public void testCreateCar_ColorNotFound() {
        when(carMapper.mapToEntity(carRequest)).thenReturn(car);
        when(brandRepository.findById(1L)).thenReturn(Optional.of(new Brand()));
        when(colorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()-> {
                    carService.create(carRequest);
                }
        );
    }

    @Test
    public void testCreateCar_ModelNotFound() {
        when(carMapper.mapToEntity(carRequest)).thenReturn(car);
        when(brandRepository.findById(1L)).thenReturn(Optional.of(new Brand()));
        when(colorRepository.findById(1L)).thenReturn(Optional.of(new Color()));
        when(modelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()-> {
            carService.create(carRequest);
        });
    }

    @Test
    public void testCreateCar_EngineTypeNotFound() {
        when(carMapper.mapToEntity(carRequest)).thenReturn(car);
        when(brandRepository.findById(1L)).thenReturn(Optional.of(new Brand()));
        when(colorRepository.findById(1L)).thenReturn(Optional.of(new Color()));
        when(modelRepository.findById(1L)).thenReturn(Optional.of(new Model()));
        when(engineTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()-> {
            carService.create(carRequest);
        });
    }
    @Test
    public void testCreateCar_CarStatusNotFound() {
        when(carMapper.mapToEntity(carRequest)).thenReturn(car);
        when(brandRepository.findById(1L)).thenReturn(Optional.of(new Brand()));
        when(colorRepository.findById(1L)).thenReturn(Optional.of(new Color()));
        when(modelRepository.findById(1L)).thenReturn(Optional.of(new Model()));
        when(engineTypeRepository.findById(1L)).thenReturn(Optional.of(new EngineType()));
        when(carStatusRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()-> {
            carService.create(carRequest);
        });
    }
}
















