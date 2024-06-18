package com.aston.rapidride.entity.DAO;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Favorites {

    @EmbeddedId
    private UserCompositeKey userCompositeKey;


}