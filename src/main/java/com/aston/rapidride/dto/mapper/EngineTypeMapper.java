package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.EngineTypeRequest;
import com.aston.rapidride.dto.response.EngineTypeResponse;
import com.aston.rapidride.entity.EngineType;
import org.springframework.stereotype.Component;

@Component
public class EngineTypeMapper {

    public EngineTypeResponse mapToResponse(EngineType engineType) {
        EngineTypeResponse response = new EngineTypeResponse();
        response.setId(engineType.getId());
        response.setName(engineType.getName());

        return response;
    }

    public EngineType mapRequestToEntity(EngineTypeRequest request) {
        EngineType engineType = new EngineType();
        engineType.setName(request.getName());

        return engineType;
    }
}
