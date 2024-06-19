package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.BrandRequest;
import com.aston.rapidride.dto.response.BrandResponse;

import java.util.List;

public interface BrandService {

    BrandResponse getById(Long id);

    List<BrandResponse> getAll();

    void create(BrandRequest request);

    void update(Long id, BrandRequest request);

    BrandResponse getByName(String name);
}
