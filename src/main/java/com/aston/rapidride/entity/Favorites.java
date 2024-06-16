package com.aston.rapidride.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Favorites {

    @EmbeddedId
    private UserCompositeKey userCompositeKey;


}