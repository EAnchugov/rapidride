package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.EngineTypeMapper;
import com.aston.rapidride.dto.request.EngineTypeRequest;
import com.aston.rapidride.dto.response.EngineTypeResponse;
import com.aston.rapidride.entity.EngineType;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.EngineTypeRepository;
import com.aston.rapidride.utility.TextConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EngineTypeServiceImplTest {

    @Mock
    private EngineTypeRepository repository;

    @Mock
    private EngineTypeMapper mapper;

    @InjectMocks
    private EngineTypeServiceImpl engineTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById_ShouldReturnEngineTypeResponse_WhenEngineTypeExists() {
        Long engineTypeId = 1L;
        EngineType engineType = new EngineType();
        EngineTypeResponse engineTypeResponse = new EngineTypeResponse();

        when(repository.findById(engineTypeId)).thenReturn(Optional.of(engineType));
        when(mapper.mapToResponse(engineType)).thenReturn(engineTypeResponse);

        EngineTypeResponse result = engineTypeService.getById(engineTypeId);

        assertEquals(engineTypeResponse, result);
        verify(repository).findById(engineTypeId);
        verify(mapper).mapToResponse(engineType);
    }

    @Test
    void getById_ShouldThrowNotFoundException_WhenEngineTypeDoesNotExist() {
        Long engineTypeId = 1L;

        when(repository.findById(engineTypeId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> engineTypeService.getById(engineTypeId));
        assertEquals(TextConstants.ENGINE_TYPE_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(engineTypeId);
    }

    @Test
    void getAll_ShouldReturnListOfEngineTypeResponses() {
        List<EngineType> engineTypes = List.of(new EngineType());
        List<EngineTypeResponse> engineTypeResponses = List.of(new EngineTypeResponse());

        when(repository.findAll()).thenReturn(engineTypes);
        when(mapper.mapToResponse(any(EngineType.class))).thenReturn(engineTypeResponses.get(0));

        List<EngineTypeResponse> result = engineTypeService.getAll();

        assertEquals(engineTypeResponses, result);
        verify(repository).findAll();
        verify(mapper, times(engineTypes.size())).mapToResponse(any(EngineType.class));
    }

    @Test
    void create_ShouldSaveEngineType() {
        EngineTypeRequest request = new EngineTypeRequest();
        EngineType engineType = new EngineType();

        when(mapper.mapRequestToEntity(request)).thenReturn(engineType);

        engineTypeService.create(request);

        verify(repository).save(engineType);
    }

    @Test
    void update_ShouldUpdateEngineType_WhenEngineTypeExists() {
        Long engineTypeId = 1L;
        EngineTypeRequest request = new EngineTypeRequest();
        EngineType engineType = new EngineType();

        when(repository.findById(engineTypeId)).thenReturn(Optional.of(engineType));

        engineTypeService.update(engineTypeId, request);

        verify(repository).findById(engineTypeId);
        verify(repository).save(engineType);
        assertEquals(request.getName(), engineType.getName());
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenEngineTypeDoesNotExist() {
        Long engineTypeId = 1L;
        EngineTypeRequest request = new EngineTypeRequest();

        when(repository.findById(engineTypeId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> engineTypeService.update(engineTypeId, request));
        assertEquals(TextConstants.ENGINE_TYPE_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(engineTypeId);
    }

    @Test
    void getByName_ShouldReturnEngineTypeResponse_WhenEngineTypeExists() {
        String name = "Diesel";
        EngineType engineType = new EngineType();
        EngineTypeResponse engineTypeResponse = new EngineTypeResponse();

        when(repository.findEngineTypeByName(name)).thenReturn(engineType);
        when(mapper.mapToResponse(engineType)).thenReturn(engineTypeResponse);

        EngineTypeResponse result = engineTypeService.getByName(name);

        assertEquals(engineTypeResponse, result);
        verify(repository).findEngineTypeByName(name);
        verify(mapper).mapToResponse(engineType);
    }

    @Test
    void getByName_ShouldThrowNotFoundException_WhenEngineTypeDoesNotExist() {
        String name = "Diesel";

        when(repository.findEngineTypeByName(name)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> engineTypeService.getByName(name));
        assertEquals(TextConstants.ENGINE_TYPE_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findEngineTypeByName(name);
    }
}
