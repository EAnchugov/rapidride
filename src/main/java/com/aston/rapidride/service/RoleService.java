package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.RoleRequest;
import com.aston.rapidride.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse getById(Long id);

    List<RoleResponse> getAll();

    void create(RoleRequest request);

    void update(Long id, RoleRequest request);

    RoleResponse getByName(String name);
}
