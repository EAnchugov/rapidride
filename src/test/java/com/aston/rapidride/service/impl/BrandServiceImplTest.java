package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.BrandMapper;
import com.aston.rapidride.dto.request.BrandRequest;
import com.aston.rapidride.dto.response.BrandResponse;
import com.aston.rapidride.entity.Brand;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.BrandRepository;
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
public class BrandServiceImplTest {

    @Mock
    BrandRepository repository;

    @Mock
    BrandMapper mapper;

    @InjectMocks
    BrandServiceImpl service;

    private BrandRequest request;

    private BrandResponse response;

    private Brand brand;

    @BeforeEach
    void init() {
        request = new BrandRequest();
        request.setName("Porsche");

        brand = new Brand();
        brand.setName("Porsche");

        response = new BrandResponse();
        response.setId(1L);
        response.setName("Porsche");
    }

    @Test
    void createColor_successTest() {
        when(mapper.mapRequestToEntity(request)).thenReturn(brand);

        service.create(request);

        verify(mapper).mapRequestToEntity(request);
        verify(repository).save(brand);
    }

    @Test
    void updateColor_successTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Brand()));
        when(mapper.mapToResponse(any(Brand.class))).thenReturn(response);

        service.update(1L, request);
        BrandResponse result = service.getById(1L);

        assertEquals(response, result);
    }

    @Test
    public void findById_successTes() {
        when(repository.findById(1L)).thenReturn(Optional.of(brand));
        when(mapper.mapToResponse(any(Brand.class))).thenReturn(response);

        BrandResponse result = service.getById(1L);

        verify(repository).findById(1L);
        verify(mapper).mapToResponse(brand);

        assertEquals(response, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            service.getById(1L);
        });

        assertEquals("Brand not found", thrown.getMessage());
        verify(repository).findById(1L);
        verify(mapper, never()).mapToResponse(any(Brand.class));
    }


    @Test
    public void testFindAllSuccess() {
        List<Brand> brands = Collections.singletonList(brand);
        when(repository.findAll()).thenReturn(brands);
        when(mapper.mapToResponse(brand)).thenReturn(response);

        List<BrandResponse> result = service.getAll();

        verify(repository).findAll();
        verify(mapper).mapToResponse(brand);

        assertEquals(1, result.size());
        assertTrue(result.contains(response));
    }

    @Test
    public void testFindAllEmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<BrandResponse> result = service.getAll();

        verify(repository).findAll();
        verify(mapper, never()).mapToResponse(any(Brand.class));

        assertTrue(result.isEmpty());
    }
}
