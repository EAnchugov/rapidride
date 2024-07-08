package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.BrandMapper;
import com.aston.rapidride.dto.request.BrandRequest;
import com.aston.rapidride.dto.response.BrandResponse;
import com.aston.rapidride.entity.Brand;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.aston.rapidride.utility.TextConstants.BRAND_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BrandServiceImplTest {

    @Mock
    private BrandRepository repository;

    @Mock
    private BrandMapper mapper;

    @InjectMocks
    private BrandServiceImpl brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById_ShouldReturnBrandResponse_WhenBrandExists() {
        Long brandId = 1L;
        Brand brand = new Brand();
        BrandResponse brandResponse = new BrandResponse();

        when(repository.findById(brandId)).thenReturn(Optional.of(brand));
        when(mapper.mapToResponse(brand)).thenReturn(brandResponse);

        BrandResponse result = brandService.getById(brandId);

        assertEquals(brandResponse, result);
        verify(repository).findById(brandId);
        verify(mapper).mapToResponse(brand);
    }

    @Test
    void getById_ShouldThrowNotFoundException_WhenBrandDoesNotExist() {
        Long brandId = 1L;

        when(repository.findById(brandId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> brandService.getById(brandId));
        assertEquals(BRAND_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(brandId);
    }

    @Test
    void getAll_ShouldReturnListOfBrandResponses() {
        List<Brand> brands = List.of(new Brand());
        List<BrandResponse> brandResponses = List.of(new BrandResponse());

        when(repository.findAll()).thenReturn(brands);
        when(mapper.mapToResponse(any(Brand.class))).thenReturn(brandResponses.get(0));

        List<BrandResponse> result = brandService.getAll();

        assertEquals(brandResponses, result);
        verify(repository).findAll();
        verify(mapper).mapToResponse(any(Brand.class));
    }

    @Test
    void create_ShouldSaveBrand() {
        BrandRequest request = new BrandRequest();
        Brand brand = new Brand();

        when(mapper.mapRequestToEntity(request)).thenReturn(brand);

        brandService.create(request);

        verify(repository).save(brand);
    }

    @Test
    void update_ShouldUpdateBrand_WhenBrandExists() {
        Long brandId = 1L;
        BrandRequest request = new BrandRequest();
        Brand brand = new Brand();

        when(repository.findById(brandId)).thenReturn(Optional.of(brand));

        brandService.update(brandId, request);

        verify(repository).findById(brandId);
        verify(repository).save(brand);
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenBrandDoesNotExist() {
        Long brandId = 1L;
        BrandRequest request = new BrandRequest();

        when(repository.findById(brandId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> brandService.update(brandId, request));
        assertEquals(BRAND_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(brandId);
    }

    @Test
    void getByName_ShouldReturnBrandResponse_WhenBrandExists() {
        String name = "BrandName";
        Brand brand = new Brand();
        BrandResponse brandResponse = new BrandResponse();

        when(repository.findBrandByName(name)).thenReturn(brand);
        when(mapper.mapToResponse(brand)).thenReturn(brandResponse);

        BrandResponse result = brandService.getByName(name);

        assertEquals(brandResponse, result);
        verify(repository).findBrandByName(name);
        verify(mapper).mapToResponse(brand);
    }

    @Test
    void getByName_ShouldThrowNotFoundException_WhenBrandDoesNotExist() {
        String name = "BrandName";

        when(repository.findBrandByName(name)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> brandService.getByName(name));
        assertEquals(BRAND_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findBrandByName(name);
    }
}
