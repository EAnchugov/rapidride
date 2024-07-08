package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.CarMapper;
import com.aston.rapidride.dto.request.CarRequest;
import com.aston.rapidride.dto.response.CarResponse;
import com.aston.rapidride.entity.*;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    private Car car2;
    private CarResponse carResponse;
    private CarResponse carResponse2;

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

        car2 = new Car();
        car2.setId(2L);
        car2.setVin("1HGCM82633A654321");
        car2.setPower(200L);
        car2.setYear(2021);
        car2.setRegistrationNumber("XYZ789");
        car2.setPrice(30000);
        car2.setPhoto("photo_url2");

        carResponse = CarResponse.builder()
                .id(1L)
                .vin("1HGCM82633A123456")
                .power(150L)
                .year(2020)
                .registrationNumber("ABC123")
                .price(20000)
                .photo("photo_url")
                .brandId("1")
                .colorId("1")
                .modelId("1")
                .engineTypeId("1")
                .statusId("1")
                .build();

        carResponse2 = CarResponse.builder()
                .id(2L)
                .vin("1HGCM82633A654321")
                .power(200L)
                .year(2021)
                .registrationNumber("XYZ789")
                .price(30000)
                .photo("photo_url2")
                .brandId("2")
                .colorId("2")
                .modelId("2")
                .engineTypeId("2")
                .statusId("2")
                .build();
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

        assertThrows(NotFoundException.class, () -> {
            carService.create(carRequest);
        });
    }

    @Test()
    public void testCreateCar_ColorNotFound() {
        when(carMapper.mapToEntity(carRequest)).thenReturn(car);
        when(brandRepository.findById(1L)).thenReturn(Optional.of(new Brand()));
        when(colorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
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

        assertThrows(NotFoundException.class, () -> {
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

        assertThrows(NotFoundException.class, () -> {
            carService.create(carRequest);
        });
        verify(engineTypeRepository).findById(1L);
    }

    @Test
    public void testCreateCar_CarStatusNotFound() {
        when(carMapper.mapToEntity(carRequest)).thenReturn(car);
        when(brandRepository.findById(1L)).thenReturn(Optional.of(new Brand()));
        when(colorRepository.findById(1L)).thenReturn(Optional.of(new Color()));
        when(modelRepository.findById(1L)).thenReturn(Optional.of(new Model()));
        when(engineTypeRepository.findById(1L)).thenReturn(Optional.of(new EngineType()));
        when(carStatusRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            carService.create(carRequest);
        });
    }

    @Test
    public void testUpdate_SuccessUpdate() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(brandRepository.findById(1L)).thenReturn(Optional.of(new Brand()));
        when(colorRepository.findById(1L)).thenReturn(Optional.of(new Color()));
        when(modelRepository.findById(1L)).thenReturn(Optional.of(new Model()));
        when(engineTypeRepository.findById(1L)).thenReturn(Optional.of(new EngineType()));
        when(carStatusRepository.findById(1L)).thenReturn(Optional.of(new CarStatus()));
        when(carMapper.mapToResponse(any(Car.class))).thenReturn(carResponse);

        CarResponse result = carService.update(1L, carRequest);

        verify(carRepository).findById(1L);
        verify(carRepository).save(any(Car.class));
        verify(brandRepository).findById(1L);
        verify(colorRepository).findById(1L);
        verify(modelRepository).findById(1L);
        verify(engineTypeRepository).findById(1L);
        verify(carStatusRepository).findById(1L);
        verify(carMapper).mapToResponse(any(Car.class));

        assertEquals(carResponse, result);
    }

    @Test
    public void testUpdateCarNotFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            carService.update(1L, carRequest);
        });

        assertEquals("Car not found", thrown.getMessage());
        verify(carRepository).findById(1L);
        verify(carRepository, never()).save(any(Car.class));
    }

    @Test
    public void testFindByIdSuccess() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carMapper.mapToResponse(any(Car.class))).thenReturn(carResponse);

        CarResponse result = carService.findById(1L);

        verify(carRepository).findById(1L);
        verify(carMapper).mapToResponse(car);

        assertEquals(carResponse, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            carService.findById(1L);
        });

        assertEquals("Car not found", thrown.getMessage());
        verify(carRepository).findById(1L);
        verify(carMapper, never()).mapToResponse(any(Car.class));
    }


    @Test
    public void testFindAllSuccess() {
        List<Car> cars = Arrays.asList(car, car2);
        when(carRepository.findAll()).thenReturn(cars);
        when(carMapper.mapToResponse(car)).thenReturn(carResponse);
        when(carMapper.mapToResponse(car2)).thenReturn(carResponse2);

        List<CarResponse> result = carService.findAll();

        verify(carRepository).findAll();
        verify(carMapper).mapToResponse(car);
        verify(carMapper).mapToResponse(car2);

        assertEquals(2, result.size());
        assertTrue(result.contains(carResponse));
        assertTrue(result.contains(carResponse2));
    }

    @Test
    public void testFindAllEmptyList() {
        when(carRepository.findAll()).thenReturn(Collections.emptyList());

        List<CarResponse> result = carService.findAll();

        verify(carRepository).findAll();
        verify(carMapper, never()).mapToResponse(any(Car.class));

        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteByIdSuccess() {
        when(carRepository.existsById(1L)).thenReturn(true);

        carService.deleteById(1L);

        verify(carRepository).deleteById(1L);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(carRepository.existsById(1L)).thenReturn(false);

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            carService.deleteById(1L);
        });

        assertEquals("Car not found", thrown.getMessage());
        verify(carRepository, never()).deleteById(1L);
    }
}
















