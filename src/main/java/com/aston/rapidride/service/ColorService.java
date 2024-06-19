package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.ColorRequest;
import com.aston.rapidride.dto.response.ColorResponse;

import java.util.List;

public interface ColorService {

    ColorResponse getById(Long id);

    List<ColorResponse> getAll();

    void create(ColorRequest request);

    void update(Long id, ColorRequest request);

    ColorResponse getByName(String name);
}
