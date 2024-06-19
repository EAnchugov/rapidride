package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.BrandRequest;
import com.aston.rapidride.dto.response.BrandResponse;
import com.aston.rapidride.entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public BrandResponse mapToResponse(Brand brand) {
        BrandResponse response = new BrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());

        return response;
    }

    public Brand mapRequestToEntity(BrandRequest request) {
        Brand brand = new Brand();
        brand.setName(request.getName());

        return brand;
    }
}
