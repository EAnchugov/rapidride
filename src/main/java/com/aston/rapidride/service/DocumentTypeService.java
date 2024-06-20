package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.DocumentTypeRequest;
import com.aston.rapidride.dto.response.DocumentTypeResponse;

import java.util.List;

public interface DocumentTypeService {
    DocumentTypeResponse getById(Long id);

    List<DocumentTypeResponse> getAll();

    void create(DocumentTypeRequest request);

    void update(Long id, DocumentTypeRequest request);

    DocumentTypeResponse getByName(String name);
}
