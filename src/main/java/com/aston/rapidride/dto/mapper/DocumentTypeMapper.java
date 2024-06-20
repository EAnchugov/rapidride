package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.DocumentTypeRequest;
import com.aston.rapidride.dto.response.DocumentTypeResponse;
import com.aston.rapidride.entity.DocumentType;
import org.springframework.stereotype.Component;

@Component
public class DocumentTypeMapper {public DocumentTypeResponse mapToResponse(DocumentType documentType) {
    DocumentTypeResponse response = new DocumentTypeResponse();
    response.setId(documentType.getId());
    response.setName(documentType.getName());

    return response;
}

    public DocumentType mapRequestToEntity(DocumentTypeRequest request) {
        DocumentType documentType = new DocumentType();
        documentType.setName(request.getName());

        return documentType;
    }
}
