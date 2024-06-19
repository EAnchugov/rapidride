package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.ModelMapper;
import com.aston.rapidride.dto.request.ModelRequest;
import com.aston.rapidride.dto.response.ModelResponse;
import com.aston.rapidride.entity.Model;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.ModelRepository;
import com.aston.rapidride.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aston.rapidride.utility.TextConstants.MODEL_NOT_FOUND;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository repository;
    private final ModelMapper mapper;

    @Override
    public ModelResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToResponse)
                .orElseThrow(() -> new NotFoundException(MODEL_NOT_FOUND.get()));
    }

    @Override
    public List<ModelResponse> getAll() {
        List<Model> models = repository.findAll();

        return models.stream()
                .map(mapper::mapToResponse)
                .toList();
    }

    @Override
    public void create(ModelRequest request) {
        repository.save(mapper.mapRequestToEntity(request));
    }

    @Override
    public void update(Long id, ModelRequest request) {
        Model model = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(MODEL_NOT_FOUND.get()));
        model.setName(request.getName());
        repository.save(model);
    }

    @Override
    public ModelResponse getByName(String name) {
        Model model = repository.findModelByName(name);

        if (model != null) {
            return mapper.mapToResponse(model);
        } else {
            throw new NotFoundException(MODEL_NOT_FOUND.get());
        }
    }
}
