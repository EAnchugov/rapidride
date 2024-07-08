package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.UserDocumentMapper;
import com.aston.rapidride.dto.request.UserDocumentRequest;
import com.aston.rapidride.dto.response.UserDocumentResponse;
import com.aston.rapidride.entity.DocumentType;
import com.aston.rapidride.entity.UserDocument;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.UserDocumentRepository;
import com.aston.rapidride.utility.TextConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDocumentServiceImplTest {
    @Mock
    private UserDocumentRepository repository;

    @Mock
    private UserDocumentMapper mapper;

    @InjectMocks
    private UserDocumentServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById_UserDocumentFound() {
        Long id = 1L;
        UserDocument userDocument = new UserDocument();
        UserDocumentResponse response = new UserDocumentResponse();

        when(repository.findById(id)).thenReturn(Optional.of(userDocument));
        when(mapper.mapToResponse(userDocument)).thenReturn(response);

        UserDocumentResponse result = service.getById(id);

        assertNotNull(result);
        assertEquals(response, result);
    }

    @Test
    void testGetById_UserDocumentNotFound() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.getById(id);
        });

        assertEquals(TextConstants.COLOR_NOT_FOUND.get(), exception.getMessage());
    }

    @Test
    void testGetAll_UserDocumentsFound() {
        UserDocument userDocument = new UserDocument();
        UserDocumentResponse response = new UserDocumentResponse();

        when(repository.findAll()).thenReturn(Collections.singletonList(userDocument));
        when(mapper.mapToResponse(userDocument)).thenReturn(response);

        List<UserDocumentResponse> result = service.getAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(response, result.get(0));
    }

    @Test
    void testCreate_UserDocumentCreated() {
        UserDocumentRequest request = new UserDocumentRequest();
        UserDocument userDocument = new UserDocument();

        when(mapper.mapRequestToEntity(request)).thenReturn(userDocument);

        service.create(request);

        verify(repository, times(1)).save(userDocument);
    }

    @Test
    void testUpdate_UserDocumentNotFound() {
        Long id = 1L;
        UserDocumentRequest request = new UserDocumentRequest();

        when(repository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.update(id, request);
        });

        assertEquals(TextConstants.USER_DOCUMENT_NOT_FOUND.get(), exception.getMessage());
    }
}
