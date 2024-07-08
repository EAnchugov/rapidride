//package com.aston.rapidride.controller;
//
//import com.aston.rapidride.dto.filter.BrandByNameFilter;
//import com.aston.rapidride.dto.request.BrandRequest;
//import com.aston.rapidride.dto.response.BrandResponse;
//import com.aston.rapidride.service.BrandService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(BrandController.class)
//public class BrandControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private BrandService brandService;
//
//    @InjectMocks
//    private BrandController brandController;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void testGetById() throws Exception {
//        Long id = 1L;
//        BrandResponse brandResponse = new BrandResponse(id, "BrandName");
//
//        when(brandService.getById(id)).thenReturn(brandResponse);
//
//        mockMvc.perform(get("/api/v1/brands/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value("BrandName"));
//    }
//
//    @Test
//    void testGetAll() throws Exception {
//        List<BrandResponse> brandResponses = Arrays.asList(
//                new BrandResponse(1L, "Brand1"),
//                new BrandResponse(2L, "Brand2")
//        );
//
//        when(brandService.getAll()).thenReturn(brandResponses);
//
//        mockMvc.perform(get("/api/v1/brands"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1L))
//                .andExpect(jsonPath("$[0].name").value("Brand1"))
//                .andExpect(jsonPath("$[1].id").value(2L))
//                .andExpect(jsonPath("$[1].name").value("Brand2"));
//    }
//
//    @Test
//    void testCreate() throws Exception {
//        BrandRequest brandRequest = new BrandRequest("BrandName");
//
//        mockMvc.perform(post("/api/v1/brands")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(brandRequest)))
//                .andExpect(status().isCreated());
//
//        verify(brandService, times(1)).create(any(BrandRequest.class));
//    }
//
//    @Test
//    void testUpdate() throws Exception {
//        Long id = 1L;
//        BrandRequest brandRequest = new BrandRequest("UpdatedBrandName");
//
//        mockMvc.perform(put("/api/v1/brands/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(brandRequest)))
//                .andExpect(status().isOk());
//
//        verify(brandService, times(1)).update(eq(id), any(BrandRequest.class));
//    }
//
//    @Test
//    void testGetByName() throws Exception {
//        String name = "BrandName";
//        BrandResponse brandResponse = new BrandResponse(1L, name);
//        BrandByNameFilter filter = new BrandByNameFilter(name);
//
//        when(brandService.getByName(name)).thenReturn(brandResponse);
//
//        mockMvc.perform(post("/api/v1/brands/name")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(filter)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.name").value(name));
//    }
//}
