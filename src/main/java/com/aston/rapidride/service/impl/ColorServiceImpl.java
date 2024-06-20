package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.ColorMapper;
import com.aston.rapidride.dto.request.ColorRequest;
import com.aston.rapidride.dto.response.ColorResponse;
import com.aston.rapidride.entity.Color;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.ColorRepository;
import com.aston.rapidride.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aston.rapidride.utility.TextConstants.COLOR_NOT_FOUND;

@Service
@AllArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository repository;
    private final ColorMapper mapper;

    @Override
    public ColorResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToResponse)
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.get()));
    }

    @Override
    public List<ColorResponse> getAll() {
        List<Color> colors = repository.findAll();

        return colors.stream()
                .map(mapper::mapToResponse)
                .toList();
    }

    @Override
    public void create(ColorRequest request) {
        repository.save(mapper.mapRequestToEntity(request));
    }

    @Override
    public void update(Long id, ColorRequest request) {
        Color color = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.get()));
        color.setName(request.getName());
        repository.save(color);
    }

    @Override
    public ColorResponse getByName(String name) {
        Color color = repository.findColorByName(name);

        if (color != null) {
            return mapper.mapToResponse(color);
        } else {
            throw new NotFoundException(COLOR_NOT_FOUND.get());
        }
    }
}
