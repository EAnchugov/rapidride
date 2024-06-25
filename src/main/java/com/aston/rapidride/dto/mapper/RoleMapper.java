package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.RoleRequest;
import com.aston.rapidride.dto.response.RoleResponse;
import com.aston.rapidride.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponse mapToResponse(Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());

        return response;
    }

    public Role mapRequestToEntity(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());

        return role;
    }
}
