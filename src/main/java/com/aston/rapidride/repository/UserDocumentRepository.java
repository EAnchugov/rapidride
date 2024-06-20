package com.aston.rapidride.repository;

import com.aston.rapidride.entity.DocumentType;
import com.aston.rapidride.entity.UserDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDocumentRepository extends JpaRepository<UserDocument, Long> {
    UserDocument findUserDocumentByName(String name);
}
