package com.aston.rapidride.controller;

import com.aston.rapidride.dto.mapper.FineMapper;
import com.aston.rapidride.dto.request.FineRequest;
import com.aston.rapidride.dto.response.FineResponse;
import com.aston.rapidride.entity.Fine;
import com.aston.rapidride.service.FineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/manager/fines")
@RequiredArgsConstructor
public class ManagerFineController {

    private final FineService fineService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FineResponse getFineById(@PathVariable Long id) {
        return FineMapper.toFineResponse(fineService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFine(@RequestBody @Valid FineRequest request) {
        fineService.createFine(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateFine(@RequestBody @Valid FineRequest request) {
        fineService.updateFine(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FineResponse> getAllFines() {
        List<Fine> fines = fineService.getAllFine();
        return fines.stream().map(FineMapper::toFineResponse).collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public List<FineResponse> getAllFinesByUser(@PathVariable Long userId) {
        List<Fine> fines = fineService.getAllFinesByUserId(userId);
        return fines.stream().map(FineMapper::toFineResponse).collect(Collectors.toList());
    }

    @GetMapping("/car/{carId}")
    public List<FineResponse> getAllFinesByCarId(@PathVariable Long carId) {

        List<Fine> fines = fineService.getAllFinesByCarId(carId);
        return fines.stream().map(FineMapper::toFineResponse).collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}/car/{carId}")
    public ResponseEntity<List<FineResponse>> getFineByUserIdAndCarId(@PathVariable Long userId,
                                                                      @PathVariable Long carId) {
        List<Fine> fines = fineService.getAllFinesByUserIdAndCarId(userId, carId);
        return new ResponseEntity<>(fines.stream().map(FineMapper::toFineResponse).toList(), HttpStatus.OK);
    }

    @GetMapping("/registration/{registrationNumber}")
    public ResponseEntity<List<FineResponse>> getFinesByRegistrationNumber(@PathVariable Long registrationNumber) {
        List<Fine> fines = fineService.getFinesByRegistrationNumber(registrationNumber);
        return new ResponseEntity<>(fines.stream().map(FineMapper::toFineResponse).toList(), HttpStatus.OK);
    }

    @GetMapping("/sum/{sum}")
    public ResponseEntity<List<FineResponse>> getAllFinesBySum(@PathVariable BigDecimal sum) {
        List<Fine> fines = fineService.getAllFinesBySum(sum);
        return new ResponseEntity<>(fines.stream().map(FineMapper::toFineResponse).toList(), HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<FineResponse>> getAllFinesByDate(@PathVariable LocalDate date) {
        List<Fine> fines = fineService.getAllByDate(date);
        return new ResponseEntity<>(fines.stream().map(FineMapper::toFineResponse).toList(), HttpStatus.OK);
    }
}
