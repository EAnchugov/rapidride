package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.CarRequest;
import com.aston.rapidride.dto.response.CarResponse;
import com.aston.rapidride.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CarRequest carRequest) {
        carService.create(carRequest);
        return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CarRequest carRequest) {
        return new ResponseEntity<>(carService.update(id, carRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(carService.findById(id), HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<CarResponse>> findAll() {
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        carService.deleteById(id);
        return new ResponseEntity<>("Successfully", HttpStatus.OK);
    }
}
