package com.aston.rapidride.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


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


    @Column(name = "user_id", nullable = false)
    @NotBlank(message = "user_id can't be empty")
    Long userID;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "first_name can't be empty")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "last_name can't be empty")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;
}
