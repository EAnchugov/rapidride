package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.ModelMapper;
import com.aston.rapidride.dto.request.ModelRequest;
import com.aston.rapidride.dto.response.ModelResponse;
import com.aston.rapidride.entity.Model;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.ModelRepository;
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
public class ModelServiceImplTest {

    @Mock
    ModelRepository repository;

    @Mock
    ModelMapper mapper;

    @InjectMocks
    ModelServiceImpl service;

    private ModelRequest request;

    private ModelResponse response;

    private Model model;

    @BeforeEach
    void init() {
        request = new ModelRequest();
        request.setName("911 turbo s");

        model = new Model();
        model.setName("911 turbo s");

        response = new ModelResponse();
        response.setId(1L);
        response.setName("911 turbo s");
    }

    @Test
    void createColor_successTest() {
        when(mapper.mapRequestToEntity(request)).thenReturn(model);

        service.create(request);

        verify(mapper).mapRequestToEntity(request);
        verify(repository).save(model);
    }

    @Test
    void updateColor_successTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Model()));
        when(mapper.mapToResponse(any(Model.class))).thenReturn(response);

        service.update(1L, request);
        ModelResponse result = service.getById(1L);

        assertEquals(response, result);
    }

    @Test
    public void findById_successTes() {
        when(repository.findById(1L)).thenReturn(Optional.of(model));
        when(mapper.mapToResponse(any(Model.class))).thenReturn(response);

        ModelResponse result = service.getById(1L);

        verify(repository).findById(1L);
        verify(mapper).mapToResponse(model);

        assertEquals(response, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            service.getById(1L);
        });

        assertEquals("Model not found", thrown.getMessage());
        verify(repository).findById(1L);
        verify(mapper, never()).mapToResponse(any(Model.class));
    }


    @Test
    public void testFindAllSuccess() {
        List<Model> models = Collections.singletonList(model);
        when(repository.findAll()).thenReturn(models);
        when(mapper.mapToResponse(model)).thenReturn(response);

        List<ModelResponse> result = service.getAll();

        verify(repository).findAll();
        verify(mapper).mapToResponse(model);

        assertEquals(1, result.size());
        assertTrue(result.contains(response));
    }

    @Test
    public void testFindAllEmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<ModelResponse> result = service.getAll();

        verify(repository).findAll();
        verify(mapper, never()).mapToResponse(any(Model.class));

        assertTrue(result.isEmpty());
    }
}
