package com.aston.rapidride.entity.rolesAndDocs;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_documents")
public class UserDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //тербуется сам user чтобы домавить отношение
    Long userID;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;
}
