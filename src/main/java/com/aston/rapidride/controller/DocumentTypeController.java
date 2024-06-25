package com.aston.rapidride.controller;

import com.aston.rapidride.dto.filter.DocumentTypeByNameFilter;
import com.aston.rapidride.dto.request.DocumentTypeRequest;
import com.aston.rapidride.dto.response.DocumentTypeResponse;
import com.aston.rapidride.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/documentType")
public class DocumentTypeController {

    private final DocumentTypeService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentTypeResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DocumentTypeResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody DocumentTypeRequest request) {
        service.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id, @Valid @RequestBody DocumentTypeRequest request) {
        service.update(id, request);
    }

    @PostMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public DocumentTypeResponse getByName(
            @Valid @RequestBody DocumentTypeByNameFilter filter) {
        return service.getByName(filter.getName());
    }
}
