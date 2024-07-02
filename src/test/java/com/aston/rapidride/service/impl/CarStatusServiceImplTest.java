package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.CarStatusMapper;
import com.aston.rapidride.dto.request.CarStatusRequest;
import com.aston.rapidride.dto.response.CarStatusResponse;
import com.aston.rapidride.entity.CarStatus;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.CarStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CarStatusServiceImplTest {

    @Mock
    private CarStatusRepository carStatusRepository;

    @Mock
    private CarStatusMapper mapper;

    @InjectMocks
    private CarStatusServiceImpl carStatusService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldSaveCarStatus() {
        CarStatusRequest request = new CarStatusRequest();
        CarStatus carStatus = new CarStatus();

        when(mapper.mapToEntity(request)).thenReturn(carStatus);

        carStatusService.create(request);

        verify(carStatusRepository).save(carStatus);
    }

    @Test
    void update_ShouldReturnUpdatedCarStatusResponse_WhenCarStatusExists() {
        Long id = 1L;
        CarStatusRequest request = new CarStatusRequest();
        CarStatus carStatus = new CarStatus();
        CarStatusResponse carStatusResponse = new CarStatusResponse();

        when(carStatusRepository.findById(id)).thenReturn(Optional.of(carStatus));
        when(mapper.mapToResponse(carStatus)).thenReturn(carStatusResponse);

        CarStatusResponse result = carStatusService.update(id, request);

        assertEquals(carStatusResponse, result);
        verify(carStatusRepository).findById(id);
        verify(carStatusRepository).save(carStatus);
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenCarStatusDoesNotExist() {
        Long id = 1L;
        CarStatusRequest request = new CarStatusRequest();

        when(carStatusRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> carStatusService.update(id, request));
        assertEquals("Car status not found", exception.getMessage());
        verify(carStatusRepository).findById(id);
    }

    @Test
    void findById_ShouldReturnCarStatusResponse_WhenCarStatusExists() {
        Long id = 1L;
        CarStatus carStatus = new CarStatus();
        CarStatusResponse carStatusResponse = new CarStatusResponse();

        when(carStatusRepository.findById(id)).thenReturn(Optional.of(carStatus));
        when(mapper.mapToResponse(carStatus)).thenReturn(carStatusResponse);

        CarStatusResponse result = carStatusService.findById(id);

        assertEquals(carStatusResponse, result);
        verify(carStatusRepository).findById(id);
        verify(mapper).mapToResponse(carStatus);
    }

    @Test
    void findById_ShouldThrowNotFoundException_WhenCarStatusDoesNotExist() {
        Long id = 1L;

        when(carStatusRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> carStatusService.findById(id));
        assertEquals("Car status not found", exception.getMessage());
        verify(carStatusRepository).findById(id);
    }

    @Test
    void findAll_ShouldReturnListOfCarStatusResponses() {
        List<CarStatus> carStatuses = List.of(new CarStatus());
        List<CarStatusResponse> carStatusResponses = List.of(new CarStatusResponse());

        when(carStatusRepository.findAll()).thenReturn(carStatuses);
        when(mapper.mapToResponse(any(CarStatus.class))).thenReturn(carStatusResponses.get(0));

        List<CarStatusResponse> result = carStatusService.findAll();

        assertEquals(carStatusResponses, result);
        verify(carStatusRepository).findAll();
        verify(mapper, times(carStatuses.size())).mapToResponse(any(CarStatus.class));
    }

    @Test
    void deleteById_ShouldDeleteCarStatus() {
        Long id = 1L;

        carStatusService.deleteById(id);

        verify(carStatusRepository).deleteById(id);
    }
}
