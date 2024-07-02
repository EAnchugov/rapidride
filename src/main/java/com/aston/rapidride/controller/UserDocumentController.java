package com.aston.rapidride.controller;


import com.aston.rapidride.dto.request.UserDocumentRequest;
import com.aston.rapidride.dto.response.UserDocumentResponse;
import com.aston.rapidride.service.UserDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/userDocuments")
public class UserDocumentController {
    private final UserDocumentService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDocumentResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDocumentResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody UserDocumentRequest request) {
        service.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id, @Valid @RequestBody UserDocumentRequest request) {
        service.update(id, request);
    }


}
