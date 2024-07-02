package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.DocumentTypeMapper;
import com.aston.rapidride.dto.request.DocumentTypeRequest;
import com.aston.rapidride.dto.response.DocumentTypeResponse;
import com.aston.rapidride.entity.DocumentType;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.DocumentTypeRepository;
import com.aston.rapidride.utility.TextConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DocumentTypeServiceImplTest {

    @Mock
    private DocumentTypeRepository repository;

    @Mock
    private DocumentTypeMapper mapper;

    @InjectMocks
    private DocumentTypeServiceImpl documentTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById_ShouldReturnDocumentTypeResponse_WhenDocumentTypeExists() {
        Long documentTypeId = 1L;
        DocumentType documentType = new DocumentType();
        DocumentTypeResponse documentTypeResponse = new DocumentTypeResponse();

        when(repository.findById(documentTypeId)).thenReturn(Optional.of(documentType));
        when(mapper.mapToResponse(documentType)).thenReturn(documentTypeResponse);

        DocumentTypeResponse result = documentTypeService.getById(documentTypeId);

        assertEquals(documentTypeResponse, result);
        verify(repository).findById(documentTypeId);
        verify(mapper).mapToResponse(documentType);
    }

    @Test
    void getById_ShouldThrowNotFoundException_WhenDocumentTypeDoesNotExist() {
        Long documentTypeId = 1L;

        when(repository.findById(documentTypeId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> documentTypeService.getById(documentTypeId));
        assertEquals(TextConstants.COLOR_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(documentTypeId);
    }

    @Test
    void getAll_ShouldReturnListOfDocumentTypeResponses() {
        List<DocumentType> documentTypes = List.of(new DocumentType());
        List<DocumentTypeResponse> documentTypeResponses = List.of(new DocumentTypeResponse());

        when(repository.findAll()).thenReturn(documentTypes);
        when(mapper.mapToResponse(any(DocumentType.class))).thenReturn(documentTypeResponses.get(0));

        List<DocumentTypeResponse> result = documentTypeService.getAll();

        assertEquals(documentTypeResponses, result);
        verify(repository).findAll();
        verify(mapper, times(documentTypes.size())).mapToResponse(any(DocumentType.class));
    }

    @Test
    void create_ShouldSaveDocumentType() {
        DocumentTypeRequest request = new DocumentTypeRequest();
        DocumentType documentType = new DocumentType();

        when(mapper.mapRequestToEntity(request)).thenReturn(documentType);

        documentTypeService.create(request);

        verify(repository).save(documentType);
    }

    @Test
    void update_ShouldUpdateDocumentType_WhenDocumentTypeExists() {
        Long documentTypeId = 1L;
        DocumentTypeRequest request = new DocumentTypeRequest();
        DocumentType documentType = new DocumentType();

        when(repository.findById(documentTypeId)).thenReturn(Optional.of(documentType));

        documentTypeService.update(documentTypeId, request);

        verify(repository).findById(documentTypeId);
        verify(repository).save(documentType);
        assertEquals(request.getName(), documentType.getName());
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenDocumentTypeDoesNotExist() {
        Long documentTypeId = 1L;
        DocumentTypeRequest request = new DocumentTypeRequest();

        when(repository.findById(documentTypeId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> documentTypeService.update(documentTypeId, request));
        assertEquals(TextConstants.COLOR_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findById(documentTypeId);
    }

    @Test
    void getByName_ShouldReturnDocumentTypeResponse_WhenDocumentTypeExists() {
        String name = "Passport";
        DocumentType documentType = new DocumentType();
        DocumentTypeResponse documentTypeResponse = new DocumentTypeResponse();

        when(repository.findDocumentTypeByName(name)).thenReturn(documentType);
        when(mapper.mapToResponse(documentType)).thenReturn(documentTypeResponse);

        DocumentTypeResponse result = documentTypeService.getByName(name);

        assertEquals(documentTypeResponse, result);
        verify(repository).findDocumentTypeByName(name);
        verify(mapper).mapToResponse(documentType);
    }

    @Test
    void getByName_ShouldThrowNotFoundException_WhenDocumentTypeDoesNotExist() {
        String name = "Passport";

        when(repository.findDocumentTypeByName(name)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> documentTypeService.getByName(name));
        assertEquals(TextConstants.DOCUMENT_TYPE_NOT_FOUND.get(), exception.getMessage());
        verify(repository).findDocumentTypeByName(name);
    }
}
