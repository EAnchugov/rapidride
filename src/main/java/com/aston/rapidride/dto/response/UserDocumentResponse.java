package com.aston.rapidride.dto.response;

import com.aston.rapidride.entity.DocumentType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDocumentResponse {

    private Long id;

    @NotBlank(message = "user_id can't be empty")
    Long userID;

    @NotBlank(message = "first_name can't be empty")
    private String firstName;


    @NotBlank(message = "last_name can't be empty")
    private String lastName;

    @NotBlank(message = "document_type can't be empty")
    private DocumentType documentType;
}
