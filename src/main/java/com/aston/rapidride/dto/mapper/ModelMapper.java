package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.ModelRequest;
import com.aston.rapidride.dto.response.ModelResponse;
import com.aston.rapidride.entity.Model;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public ModelResponse mapToResponse(Model model) {
        ModelResponse response = new ModelResponse();
        response.setId(model.getId());
        response.setName(model.getName());

        return response;
    }

    public Model mapRequestToEntity(ModelRequest request) {
        Model model = new Model();
        model.setName(request.getName());

        return model;
    }
}
