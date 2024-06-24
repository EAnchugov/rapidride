package com.aston.rapidride.controller;

import com.aston.rapidride.dto.filter.EngineTypeByNameFilter;
import com.aston.rapidride.dto.request.EngineTypeRequest;
import com.aston.rapidride.dto.response.EngineTypeResponse;
import com.aston.rapidride.service.EngineTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/engine-types")
public class EngineTypeController {

    private final EngineTypeService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EngineTypeResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EngineTypeResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody EngineTypeRequest request) {
        service.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id, @Valid @RequestBody EngineTypeRequest request) {
        service.update(id, request);
    }

    @PostMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public EngineTypeResponse getByName(
            @Valid @RequestBody EngineTypeByNameFilter filter) {
        return service.getByName(filter.getName());
    }
}
