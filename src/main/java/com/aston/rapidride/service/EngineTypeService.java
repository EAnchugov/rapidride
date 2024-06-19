package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.EngineTypeRequest;
import com.aston.rapidride.dto.response.EngineTypeResponse;

import java.util.List;

public interface EngineTypeService {

    EngineTypeResponse getById(Long id);

    List<EngineTypeResponse> getAll();

    void create(EngineTypeRequest request);

    void update(Long id, EngineTypeRequest request);

    EngineTypeResponse getByName(String name);
}
