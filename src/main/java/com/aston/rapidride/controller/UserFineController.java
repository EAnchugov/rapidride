package com.aston.rapidride.controller;

import com.aston.rapidride.dto.mapper.FineMapper;
import com.aston.rapidride.dto.response.FineResponse;
import com.aston.rapidride.entity.Fine;
import com.aston.rapidride.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user/fines")
public class UserFineController {

    private final FineService fineService;

    @Autowired
    public UserFineController(FineService fineService) {
        this.fineService = fineService;
    }


    @GetMapping()
    public List<FineResponse> getAllFinesByUserId() {
        //TODO получение айдишника из юзера
  //      List<Fine> fines = fineService.getAllFinesByUserId(userId);
        List<Fine> fines = fineService.getAllFinesByUserId(1L);
        return fines.stream().map(FineMapper::toFineResponse).collect(Collectors.toList());
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<FineResponse>> getFineByUserIdAndCarId(@PathVariable Long userId, @PathVariable Long carId) {
        //TODO получение айдишника из юзера
        List<Fine> fines = fineService.getAllFinesByUserIdAndCarId(userId, carId);
        return new ResponseEntity<>(fines.stream().map(FineMapper::toFineResponse).toList(), HttpStatus.OK);
    }
}
