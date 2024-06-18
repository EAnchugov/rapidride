package com.aston.rapidride.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class UserCompositeKey implements Serializable {

    @ManyToOne
    private Long userId;

    @ManyToOne
    private Long carId;
}