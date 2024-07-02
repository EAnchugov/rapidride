package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.ColorMapper;
import com.aston.rapidride.dto.request.ColorRequest;
import com.aston.rapidride.dto.response.ColorResponse;
import com.aston.rapidride.entity.Color;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.ColorRepository;
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

class ColorServiceImplTest {

    @Mock
    private ColorRepository repository;

    @Mock
    private ColorMapper mapper;

    @InjectMocks
    private ColorServiceImpl colorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById_ShouldReturnColorResponse_WhenColorExists() {
        Long colorId = 1L;
        Color color = new Color();
        ColorResponse colorResponse = new ColorResponse();

        when(repository.findById(colorId)).thenReturn(Optional.of(color));
        when(mapper.mapToResponse(color)).thenReturn(colorResponse);

        ColorResponse result = colorService.getById(colorId);

        assertEquals(colorResponse, result);
        verify(repository).findById(colorId);
        verify(mapper).mapToResponse(color);
    }

    @Test
    void getById_ShouldThrowNotFoundException_WhenColorDoesNotExist() {
        Long colorId = 1L;

        when(repository.findById(colorId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> colorService.getById(colorId));
        assertEquals(TextConstants.COLOR_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(colorId);
    }

    @Test
    void getAll_ShouldReturnListOfColorResponses() {
        List<Color> colors = List.of(new Color());
        List<ColorResponse> colorResponses = List.of(new ColorResponse());

        when(repository.findAll()).thenReturn(colors);
        when(mapper.mapToResponse(any(Color.class))).thenReturn(colorResponses.get(0));

        List<ColorResponse> result = colorService.getAll();

        assertEquals(colorResponses, result);
        verify(repository).findAll();
        verify(mapper, times(colors.size())).mapToResponse(any(Color.class));
    }

    @Test
    void create_ShouldSaveColor() {
        ColorRequest request = new ColorRequest();
        Color color = new Color();

        when(mapper.mapRequestToEntity(request)).thenReturn(color);

        colorService.create(request);

        verify(repository).save(color);
    }

    @Test
    void update_ShouldUpdateColor_WhenColorExists() {
        Long colorId = 1L;
        ColorRequest request = new ColorRequest();
        Color color = new Color();

        when(repository.findById(colorId)).thenReturn(Optional.of(color));

        colorService.update(colorId, request);

        verify(repository).findById(colorId);
        verify(repository).save(color);
        assertEquals(request.getName(), color.getName());
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenColorDoesNotExist() {
        Long colorId = 1L;
        ColorRequest request = new ColorRequest();

        when(repository.findById(colorId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> colorService.update(colorId, request));
        assertEquals(TextConstants.COLOR_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(colorId);
    }

    @Test
    void getByName_ShouldReturnColorResponse_WhenColorExists() {
        String name = "Red";
        Color color = new Color();
        ColorResponse colorResponse = new ColorResponse();

        when(repository.findColorByName(name)).thenReturn(color);
        when(mapper.mapToResponse(color)).thenReturn(colorResponse);

        ColorResponse result = colorService.getByName(name);

        assertEquals(colorResponse, result);
        verify(repository).findColorByName(name);
        verify(mapper).mapToResponse(color);
    }

    @Test
    void getByName_ShouldThrowNotFoundException_WhenColorDoesNotExist() {
        String name = "Red";

        when(repository.findColorByName(name)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> colorService.getByName(name));
        assertEquals(TextConstants.COLOR_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findColorByName(name);
    }
}
