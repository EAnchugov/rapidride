package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.UserDocumentRequest;
import com.aston.rapidride.dto.response.UserDocumentResponse;

import java.util.List;

public interface UserDocumentService {
    UserDocumentResponse getById(Long id);

    List<UserDocumentResponse> getAll();

    void create(UserDocumentRequest request);

    void update(Long id, UserDocumentRequest request);
}
