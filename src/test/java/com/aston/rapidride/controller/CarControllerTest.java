package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.CarRequest;
import com.aston.rapidride.dto.response.CarResponse;
import com.aston.rapidride.jwt.JwtFilter;
import com.aston.rapidride.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private JwtFilter jwtFilter;

    @Autowired
    private ObjectMapper objectMapper;

    private CarRequest carRequest;
    private CarResponse carResponse;

    @BeforeEach
    public void setup() {
        carRequest = new CarRequest();
        carRequest.setVin("1HGCM82633A123456");
        carRequest.setPower(150L);
        carRequest.setYear(2020);
        carRequest.setRegistrationNumber("ABC123");
        carRequest.setPrice(20000);
        carRequest.setPhoto("photo_url");
        carRequest.setBrandId(1L);
        carRequest.setColorId(1L);
        carRequest.setModelId(1L);
        carRequest.setEngineTypeId(1L);
        carRequest.setStatusId(1L);
        carResponse = CarResponse.builder()
                .id(1L)
                .vin("1HGCM82633A123456")
                .power(150L)
                .year(2020)
                .registrationNumber("ABC123")
                .price(20000)
                .photo("photo_url")
                .brandId("1")
                .colorId("1")
                .modelId("1")
                .engineTypeId("1")
                .statusId("1")
                .build();
    }

    private RequestPostProcessor jwt() {
        return SecurityMockMvcRequestPostProcessors.jwt().jwt(jwt -> jwt
                .claims(claims -> {
                    claims.put("scope", "USER");
                    claims.put("sub", "user@example.com");
                })
        );
    }

    @Test
    @WithMockUser
    public void testCreateCar() throws Exception {
        doNothing().when(carService).create(any(CarRequest.class));

        String carRequestJson = objectMapper.writeValueAsString(carRequest);

        mockMvc.perform(post("/api/v1/cars")
                        .with(jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carRequestJson))
                .andExpect(status().isOk());

        verify(carService, times(1)).create(any(CarRequest.class));
    }

    @Test
    @WithMockUser
    public void testUpdateCar() throws Exception {
        when(carService.update(anyLong(), any(CarRequest.class))).thenReturn(carResponse);

        mockMvc.perform(put("/api/v1/cars/1")
                        .with(jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.vin").value("1HGCM82633A123456"));

        verify(carService, times(1)).update(anyLong(), any(CarRequest.class));
    }

    @Test
    @WithMockUser
    public void testFindById() throws Exception {
        when(carService.findById(anyLong())).thenReturn(carResponse);

        mockMvc.perform(get("/api/v1/cars/1")
                        .with(jwt())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.vin").value("1HGCM82633A123456"));

        verify(carService, times(1)).findById(anyLong());
    }

    @Test
    @WithMockUser
    public void testFindAll() throws Exception {
        List<CarResponse> carResponses = Arrays.asList(carResponse);
        when(carService.findAll()).thenReturn(carResponses);

        mockMvc.perform(get("/api/v1/cars")
                        .with(jwt())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].vin").value("1HGCM82633A123456"));

        verify(carService, times(1)).findAll();
    }

    @Test
    @WithMockUser
    public void testDeleteById() throws Exception {
        doNothing().when(carService).deleteById(anyLong());

        mockMvc.perform(delete("/api/v1/cars/1")
                        .with(jwt())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully"));

        verify(carService, times(1)).deleteById(anyLong());
    }
}