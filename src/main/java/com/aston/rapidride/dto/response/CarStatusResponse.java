package com.aston.rapidride.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarStatusResponse {

    private Long id;
    private String name;
}
