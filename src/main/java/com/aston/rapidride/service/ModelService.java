package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.ModelRequest;
import com.aston.rapidride.dto.response.ModelResponse;

import java.util.List;

public interface ModelService {

    ModelResponse getById(Long id);

    List<ModelResponse> getAll();

    void create(ModelRequest request);

    void update(Long id, ModelRequest request);

    ModelResponse getByName(String name);
}
