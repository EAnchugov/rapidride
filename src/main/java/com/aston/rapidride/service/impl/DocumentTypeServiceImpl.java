package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.DocumentTypeMapper;
import com.aston.rapidride.dto.request.DocumentTypeRequest;
import com.aston.rapidride.dto.response.DocumentTypeResponse;
import com.aston.rapidride.entity.DocumentType;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.DocumentTypeRepository;
import com.aston.rapidride.service.DocumentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aston.rapidride.utility.TextConstants.COLOR_NOT_FOUND;
import static com.aston.rapidride.utility.TextConstants.DOCUMENT_TYPE_NOT_FOUND;

@Service
@AllArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {
    private final DocumentTypeRepository repository;
    private final DocumentTypeMapper mapper;

    @Override
    public DocumentTypeResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToResponse)
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.get()));
    }

    @Override
    public List<DocumentTypeResponse> getAll() {
        List<DocumentType> documentTypes = repository.findAll();

        return documentTypes.stream()
                .map(mapper::mapToResponse)
                .toList();
    }

    @Override
    public void create(DocumentTypeRequest request) {
        repository.save(mapper.mapRequestToEntity(request));
    }

    @Override
    public void update(Long id, DocumentTypeRequest request) {
        DocumentType documentType = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.get()));
        documentType.setName(request.getName());
        repository.save(documentType);
    }

    @Override
    public DocumentTypeResponse getByName(String name) {
        DocumentType documentType = repository.findDocumentTypeByName(name);

        if (documentType != null) {
            return mapper.mapToResponse(documentType);
        } else {
            throw new NotFoundException(DOCUMENT_TYPE_NOT_FOUND.get());
        }
    }
}
