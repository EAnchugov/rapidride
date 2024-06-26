package com.aston.rapidride.controller;

import com.aston.rapidride.dto.filter.RoleByNameFilter;
import com.aston.rapidride.dto.request.RoleRequest;
import com.aston.rapidride.dto.response.RoleResponse;
import com.aston.rapidride.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody RoleRequest request) {
        service.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id, @Valid @RequestBody RoleRequest request) {
        service.update(id, request);
    }

    @PostMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponse getByName(
            @Valid @RequestBody RoleByNameFilter filter) {
        return service.getByName(filter.getName());
    }
}