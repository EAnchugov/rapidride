package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.UserDocumentMapper;
import com.aston.rapidride.dto.request.UserDocumentRequest;
import com.aston.rapidride.dto.response.UserDocumentResponse;
import com.aston.rapidride.entity.UserDocument;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.UserDocumentRepository;
import com.aston.rapidride.service.UserDocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aston.rapidride.utility.TextConstants.COLOR_NOT_FOUND;
import static com.aston.rapidride.utility.TextConstants.USER_DOCUMENT_NOT_FOUND;

@Service
@AllArgsConstructor
public class UserDocumentServiceImpl implements UserDocumentService {
    private final UserDocumentRepository repository;
    private final UserDocumentMapper mapper;

    @Override
    public UserDocumentResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToResponse)
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.get()));
    }

    @Override
    public List<UserDocumentResponse> getAll() {
        List<UserDocument> colors = repository.findAll();

        return colors.stream()
                .map(mapper::mapToResponse)
                .toList();
    }

    @Override
    public void create(UserDocumentRequest request) {
        repository.save(mapper.mapRequestToEntity(request));
    }

    @Override
    public void update(Long id, UserDocumentRequest request) {
        UserDocument userDocument = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_DOCUMENT_NOT_FOUND.get()));
        userDocument.setUserID(request.getUserID());
        userDocument.setDocumentType(request.getDocumentType());
        userDocument.setFirstName(request.getFirstName());
        userDocument.setLastName(request.getLastName());
        repository.save(userDocument);
    }
}
