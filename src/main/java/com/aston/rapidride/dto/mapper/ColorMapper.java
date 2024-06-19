package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.ColorRequest;
import com.aston.rapidride.dto.response.ColorResponse;
import com.aston.rapidride.entity.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorMapper {

    public ColorResponse mapToResponse(Color color) {
        ColorResponse response = new ColorResponse();
        response.setId(color.getId());
        response.setName(color.getName());

        return response;
    }

    public Color mapRequestToEntity(ColorRequest request) {
        Color color = new Color();
        color.setName(request.getName());

        return color;
    }
}
