//package com.aston.rapidride.controller;
//
//import com.aston.rapidride.dto.filter.BrandByNameFilter;
//import com.aston.rapidride.dto.request.BrandRequest;
//import com.aston.rapidride.dto.response.BrandResponse;
//import com.aston.rapidride.service.BrandService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class BrandControllerTest {
//
//    @Mock
//    private BrandService brandService;
//
//    @InjectMocks
//    private BrandController brandController;
//
//    private Validator validator;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }
//
//    @Test
//    void getById_ShouldReturnBrandResponse_WhenBrandExists() {
//        Long brandId = 1L;
//        BrandResponse brandResponse = new BrandResponse();
//
//        when(brandService.getById(brandId)).thenReturn(brandResponse);
//
//        BrandResponse response = brandController.getById(brandId);
//
//        assertEquals(brandResponse, response);
//        verify(brandService).getById(brandId);
//    }
//
//    @Test
//    void getAll_ShouldReturnListOfBrandResponses() {
//        List<BrandResponse> brandResponses = Collections.singletonList(new BrandResponse());
//
//        when(brandService.getAll()).thenReturn(brandResponses);
//
//        List<BrandResponse> response = brandController.getAll();
//
//        assertEquals(brandResponses, response);
//        verify(brandService).getAll();
//    }
//
//    @Test
//    void create_ShouldInvokeCreateMethodInService() {
//        BrandRequest brandRequest = new BrandRequest();
//
//        brandController.create(brandRequest);
//
//        verify(brandService).create(brandRequest);
//    }
//
//    @Test
//    void update_ShouldInvokeUpdateMethodInService() {
//        Long brandId = 1L;
//        BrandRequest brandRequest = new BrandRequest();
//
//        brandController.update(brandId, brandRequest);
//
//        verify(brandService).update(brandId, brandRequest);
//    }
//
//    @Test
//    void getByName_ShouldReturnBrandResponse_WhenBrandExists() {
//        String brandName = "TestBrand";
//        BrandByNameFilter filter = new BrandByNameFilter();
//        filter.setName(brandName);
//        BrandResponse brandResponse = new BrandResponse();
//
//        when(brandService.getByName(brandName)).thenReturn(brandResponse);
//
//        BrandResponse response = brandController.getByName(filter);
//
//        assertEquals(brandResponse, response);
//        verify(brandService).getByName(brandName);
//    }
//}
