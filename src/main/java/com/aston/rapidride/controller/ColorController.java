package com.aston.rapidride.controller;

import com.aston.rapidride.dto.filter.ColorByNameFilter;
import com.aston.rapidride.dto.request.ColorRequest;
import com.aston.rapidride.dto.response.ColorResponse;
import com.aston.rapidride.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/colors")
public class ColorController {

    private final ColorService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (RuntimeException e) {
            e.getMessage();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody ColorRequest request) {
        service.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id, @Valid @RequestBody ColorRequest request) {
        service.update(id, request);
    }

    @PostMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public ColorResponse getByName(
            @Valid @RequestBody ColorByNameFilter filter) {
        return service.getByName(filter.getName());
    }
}
