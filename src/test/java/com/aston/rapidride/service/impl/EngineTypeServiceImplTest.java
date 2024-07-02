package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.EngineTypeMapper;
import com.aston.rapidride.dto.request.EngineTypeRequest;
import com.aston.rapidride.dto.response.EngineTypeResponse;
import com.aston.rapidride.entity.EngineType;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.EngineTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Azimov Ruslan
 */
@ExtendWith(MockitoExtension.class)
public class EngineTypeServiceImplTest {

    @Mock
    EngineTypeRepository repository;

    @Mock
    EngineTypeMapper mapper;

    @InjectMocks
    EngineTypeServiceImpl service;

    private EngineTypeRequest request;

    private EngineTypeResponse response;

    private EngineType type;

    @BeforeEach
    void init() {
        request = new EngineTypeRequest();
        request.setName("Diesel");

        type = new EngineType();
        type.setName("Diesel");

        response = new EngineTypeResponse();
        response.setId(1L);
        response.setName("Diesel");
    }

    @Test
    void createColor_successTest() {
        when(mapper.mapRequestToEntity(request)).thenReturn(type);

        service.create(request);

        verify(mapper).mapRequestToEntity(request);
        verify(repository).save(type);
    }

    @Test
    void updateColor_successTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(new EngineType()));
        when(mapper.mapToResponse(any(EngineType.class))).thenReturn(response);

        service.update(1L, request);
        EngineTypeResponse result = service.getById(1L);

        assertEquals(response, result);
    }

    @Test
    public void findById_successTes() {
        when(repository.findById(1L)).thenReturn(Optional.of(type));
        when(mapper.mapToResponse(any(EngineType.class))).thenReturn(response);

        EngineTypeResponse result = service.getById(1L);

        verify(repository).findById(1L);
        verify(mapper).mapToResponse(type);

        assertEquals(response, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            service.getById(1L);
        });

        assertEquals("Engine type not found", thrown.getMessage());
        verify(repository).findById(1L);
        verify(mapper, never()).mapToResponse(any(EngineType.class));
    }


    @Test
    public void testFindAllSuccess() {
        List<EngineType> types = Collections.singletonList(type);
        when(repository.findAll()).thenReturn(types);
        when(mapper.mapToResponse(type)).thenReturn(response);

        List<EngineTypeResponse> result = service.getAll();

        verify(repository).findAll();
        verify(mapper).mapToResponse(type);

        assertEquals(1, result.size());
        assertTrue(result.contains(response));
    }

    @Test
    public void testFindAllEmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<EngineTypeResponse> result = service.getAll();

        verify(repository).findAll();
        verify(mapper, never()).mapToResponse(any(EngineType.class));

        assertTrue(result.isEmpty());
    }
}
