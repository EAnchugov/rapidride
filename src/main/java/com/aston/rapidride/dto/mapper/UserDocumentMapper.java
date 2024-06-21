package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.UserDocumentRequest;
import com.aston.rapidride.dto.response.UserDocumentResponse;
import com.aston.rapidride.entity.UserDocument;
import org.springframework.stereotype.Component;

@Component
public class UserDocumentMapper {
    public UserDocumentResponse mapToResponse(UserDocument userDocument) {
        UserDocumentResponse response = new UserDocumentResponse();
        response.setId(userDocument.getId());
        response.setUserID(userDocument.getUserID());
        response.setDocumentType(userDocument.getDocumentType());
        response.setFirstName(userDocument.getFirstName());
        response.setLastName(userDocument.getLastName());

        return response;
    }

    public UserDocument mapRequestToEntity(UserDocumentRequest request) {
        UserDocument userDocument = new UserDocument();
        userDocument.setUserID(request.getUserID());
        userDocument.setDocumentType(request.getDocumentType());
        userDocument.setFirstName(request.getFirstName());
        userDocument.setLastName(request.getLastName());

        return userDocument;
    }
}
