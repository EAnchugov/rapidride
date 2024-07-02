package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.ColorMapper;
import com.aston.rapidride.dto.request.ColorRequest;
import com.aston.rapidride.dto.response.ColorResponse;
import com.aston.rapidride.entity.Color;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.ColorRepository;
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
import static org.mockito.Mockito.*;

/**
 * @author Azimov Ruslan
 */
@ExtendWith(MockitoExtension.class)
public class ColorServiceImplTest {

    @Mock
    ColorRepository repository;

    @Mock
    ColorMapper mapper;

    @InjectMocks
    ColorServiceImpl service;

    private ColorRequest request;

    private ColorResponse response;

    private Color color;

    @BeforeEach
    void init() {
        request = new ColorRequest();
        request.setName("Orange");

        color = new Color();
        color.setName("Orange");

        response = new ColorResponse();
        response.setId(1L);
        response.setName("Orange");
    }

    @Test
    void createColor_successTest() {
        when(mapper.mapRequestToEntity(request)).thenReturn(color);

        service.create(request);

        verify(mapper).mapRequestToEntity(request);
        verify(repository).save(color);
    }

    @Test
    void updateColor_successTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Color()));
        when(mapper.mapToResponse(any(Color.class))).thenReturn(response);

        service.update(1L, request);
        ColorResponse result = service.getById(1L);

        assertEquals(response, result);
    }

    @Test
    public void findById_successTes() {
        when(repository.findById(1L)).thenReturn(Optional.of(color));
        when(mapper.mapToResponse(any(Color.class))).thenReturn(response);

        ColorResponse result = service.getById(1L);

        verify(repository).findById(1L);
        verify(mapper).mapToResponse(color);

        assertEquals(response, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            service.getById(1L);
        });

        assertEquals("Color not found", thrown.getMessage());
        verify(repository).findById(1L);
        verify(mapper, never()).mapToResponse(any(Color.class));
    }


    @Test
    public void testFindAllSuccess() {
        List<Color> colors = Collections.singletonList(color);
        when(repository.findAll()).thenReturn(colors);
        when(mapper.mapToResponse(color)).thenReturn(response);

        List<ColorResponse> result = service.getAll();

        verify(repository).findAll();
        verify(mapper).mapToResponse(color);

        assertEquals(1, result.size());
        assertTrue(result.contains(response));
    }

    @Test
    public void testFindAllEmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<ColorResponse> result = service.getAll();

        verify(repository).findAll();
        verify(mapper, never()).mapToResponse(any(Color.class));

        assertTrue(result.isEmpty());
    }
}
