package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.CarStatusRequest;
import com.aston.rapidride.dto.response.CarStatusResponse;
import com.aston.rapidride.service.CarStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/car-statuses")
@RestController
public class CarStatusController {

    private final CarStatusService carStatusService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CarStatusRequest request) {
        carStatusService.create(request);
        return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CarStatusRequest carStatusRequest) {
        return new ResponseEntity<>(carStatusService.update(id, carStatusRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(carStatusService.findById(id), HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<CarStatusResponse>> findAll() {
        return new ResponseEntity<>(carStatusService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        carStatusService.deleteById(id);
        return new ResponseEntity<>("Successfully", HttpStatus.OK);
    }
}
