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
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "owner", nullable = false)
    @NotBlank(message = "owner can't be empty")
    private String owner;

    @Column(name = "expire_date", nullable = false)
    @NotBlank(message = "expire date can't be empty")
    private String expireDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
