package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.EngineTypeMapper;
import com.aston.rapidride.dto.request.EngineTypeRequest;
import com.aston.rapidride.dto.response.EngineTypeResponse;
import com.aston.rapidride.entity.EngineType;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.EngineTypeRepository;
import com.aston.rapidride.service.EngineTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aston.rapidride.utility.TextConstants.ENGINE_TYPE_NOT_FOUND;

@Service
@AllArgsConstructor
public class EngineTypeServiceImpl implements EngineTypeService {

    private final EngineTypeRepository repository;
    private final EngineTypeMapper mapper;

    @Override
    public EngineTypeResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToResponse)
                .orElseThrow(() -> new NotFoundException(ENGINE_TYPE_NOT_FOUND.get()));
    }

    @Override
    public List<EngineTypeResponse> getAll() {
        List<EngineType> engineTypes = repository.findAll();

        return engineTypes.stream()
                .map(mapper::mapToResponse)
                .toList();
    }

    @Override
    public void create(EngineTypeRequest request) {
        repository.save(mapper.mapRequestToEntity(request));
    }

    @Override
    public void update(Long id, EngineTypeRequest request) {
        EngineType engineType = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ENGINE_TYPE_NOT_FOUND.get()));
        engineType.setName(request.getName());
        repository.save(engineType);
    }

    @Override
    public EngineTypeResponse getByName(String name) {
        EngineType engineType = repository.findEngineTypeByName(name);

        if (engineType != null) {
            return mapper.mapToResponse(engineType);
        } else {
            throw new NotFoundException(ENGINE_TYPE_NOT_FOUND.get());
        }
    }
}
