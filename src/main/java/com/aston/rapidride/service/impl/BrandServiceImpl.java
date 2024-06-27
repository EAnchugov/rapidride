package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.BrandMapper;
import com.aston.rapidride.dto.request.BrandRequest;
import com.aston.rapidride.dto.response.BrandResponse;
import com.aston.rapidride.entity.Brand;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.BrandRepository;
import com.aston.rapidride.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aston.rapidride.utility.TextConstants.BRAND_NOT_FOUND;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;
    private final BrandMapper mapper;

    @Override
    public BrandResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToResponse)
                .orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.get()));
    }

    @Override
    public List<BrandResponse> getAll() {
        List<Brand> brands = repository.findAll();

        return brands.stream()
                .map(mapper::mapToResponse)
                .toList();
    }

    @Override
    public void create(BrandRequest request) {
        repository.save(mapper.mapRequestToEntity(request));
    }

    @Override
    public void update(Long id, BrandRequest request) {
        Brand brand = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.get()));
        brand.setName(request.getName());
        repository.save(brand);
    }

    @Override
    public BrandResponse getByName(String name) {
        Brand brand = repository.findBrandByName(name);

        if (brand != null) {
            return mapper.mapToResponse(brand);
        } else {
            throw new NotFoundException(BRAND_NOT_FOUND.get());
        }
    }
}
