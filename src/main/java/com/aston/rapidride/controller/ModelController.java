package com.aston.rapidride.controller;

import com.aston.rapidride.dto.filter.ModelByNameFilter;
import com.aston.rapidride.dto.request.ModelRequest;
import com.aston.rapidride.dto.response.ModelResponse;
import com.aston.rapidride.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/models")
public class ModelController {

    private final ModelService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModelResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ModelResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody ModelRequest request) {
        service.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id, @Valid @RequestBody ModelRequest request) {
        service.update(id, request);
    }

    @PostMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public ModelResponse getByName(
            @Valid @RequestBody ModelByNameFilter filter) {
        return service.getByName(filter.getName());
    }
}
