package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.RoleMapper;
import com.aston.rapidride.dto.request.RoleRequest;
import com.aston.rapidride.dto.response.RoleResponse;
import com.aston.rapidride.entity.Role;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.RoleRepository;
import com.aston.rapidride.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aston.rapidride.utility.TextConstants.COLOR_NOT_FOUND;
import static com.aston.rapidride.utility.TextConstants.ROLE_NOT_FOUND;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public RoleResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToResponse)
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND.get()));
    }

    @Override
    public List<RoleResponse> getAll() {
        List<Role> roles = repository.findAll();

        return roles.stream()
                .map(mapper::mapToResponse)
                .toList();
    }

    @Override
    public void create(RoleRequest request) {
        repository.save(mapper.mapRequestToEntity(request));
    }

    @Override
    public void update(Long id, RoleRequest request) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND.get()));
        role.setName(request.getName());
        repository.save(role);
    }

    @Override
    public RoleResponse getByName(String name) {
        Role role = repository.findRoleByName(name);

        if (role != null) {
            return mapper.mapToResponse(role);
        } else {
            throw new NotFoundException(ROLE_NOT_FOUND.get());
        }
    }
}
