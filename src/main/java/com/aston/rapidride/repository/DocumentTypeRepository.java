package com.aston.rapidride.repository;

import com.aston.rapidride.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    DocumentType findDocumentTypeByName(String name);
}
