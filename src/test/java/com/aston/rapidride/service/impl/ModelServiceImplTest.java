package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.ModelMapper;
import com.aston.rapidride.dto.request.ModelRequest;
import com.aston.rapidride.dto.response.ModelResponse;
import com.aston.rapidride.entity.Model;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.ModelRepository;
import com.aston.rapidride.utility.TextConstants;
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

class ModelServiceImplTest {

    @Mock
    private ModelRepository repository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ModelServiceImpl modelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById_ShouldReturnModelResponse_WhenModelExists() {
        Long modelId = 1L;
        Model model = new Model();
        ModelResponse modelResponse = new ModelResponse();

        when(repository.findById(modelId)).thenReturn(Optional.of(model));
        when(mapper.mapToResponse(model)).thenReturn(modelResponse);

        ModelResponse result = modelService.getById(modelId);

        assertEquals(modelResponse, result);
        verify(repository).findById(modelId);
        verify(mapper).mapToResponse(model);
    }

    @Test
    void getById_ShouldThrowNotFoundException_WhenModelDoesNotExist() {
        Long modelId = 1L;

        when(repository.findById(modelId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> modelService.getById(modelId));
        assertEquals(TextConstants.MODEL_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(modelId);
    }

    @Test
    void getAll_ShouldReturnListOfModelResponses() {
        List<Model> models = List.of(new Model());
        List<ModelResponse> modelResponses = List.of(new ModelResponse());

        when(repository.findAll()).thenReturn(models);
        when(mapper.mapToResponse(any(Model.class))).thenReturn(modelResponses.get(0));

        List<ModelResponse> result = modelService.getAll();

        assertEquals(modelResponses, result);
        verify(repository).findAll();
        verify(mapper, times(models.size())).mapToResponse(any(Model.class));
    }

    @Test
    void create_ShouldSaveModel() {
        ModelRequest request = new ModelRequest();
        Model model = new Model();

        when(mapper.mapRequestToEntity(request)).thenReturn(model);

        modelService.create(request);

        verify(repository).save(model);
    }

    @Test
    void update_ShouldUpdateModel_WhenModelExists() {
        Long modelId = 1L;
        ModelRequest request = new ModelRequest();
        Model model = new Model();

        when(repository.findById(modelId)).thenReturn(Optional.of(model));

        modelService.update(modelId, request);

        verify(repository).findById(modelId);
        verify(repository).save(model);
        assertEquals(request.getName(), model.getName());
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenModelDoesNotExist() {
        Long modelId = 1L;
        ModelRequest request = new ModelRequest();

        when(repository.findById(modelId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> modelService.update(modelId, request));
        assertEquals(TextConstants.MODEL_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(modelId);
    }

    @Test
    void getByName_ShouldReturnModelResponse_WhenModelExists() {
        String name = "Sedan";
        Model model = new Model();
        ModelResponse modelResponse = new ModelResponse();

        when(repository.findModelByName(name)).thenReturn(model);
        when(mapper.mapToResponse(model)).thenReturn(modelResponse);

        ModelResponse result = modelService.getByName(name);

        assertEquals(modelResponse, result);
        verify(repository).findModelByName(name);
        verify(mapper).mapToResponse(model);
    }

    @Test
    void getByName_ShouldThrowNotFoundException_WhenModelDoesNotExist() {
        String name = "Sedan";

        when(repository.findModelByName(name)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> modelService.getByName(name));
        assertEquals(TextConstants.MODEL_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findModelByName(name);
    }
}
