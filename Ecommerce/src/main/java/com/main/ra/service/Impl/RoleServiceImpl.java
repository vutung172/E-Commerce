package com.main.ra.service.Impl;

import com.main.ra.model.Enum.RoleType;
import com.main.ra.model.entity.RoleEntity;
import com.main.ra.repository.RoleRepository;
import com.main.ra.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleEntity findByRoleName(RoleType roleName){
        return roleRepository.findRoleEntitiesByRoleName(roleName).orElse(null);
    }
}
