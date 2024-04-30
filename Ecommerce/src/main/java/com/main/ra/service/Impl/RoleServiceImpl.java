package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.model.Enum.RoleType;
import com.main.ra.model.entity.RoleEntity;
import com.main.ra.repository.RoleRepository;
import com.main.ra.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<RoleEntity> findAll(){
        return roleRepository.findAll();
    }

    public RoleEntity findByRoleName(RoleType roleName){
        try{
            return roleRepository.findRoleEntitiesByRoleName(roleName).orElse(null);
        }catch (Exception e){
            throw new BaseException("exception.response.DataNotFound", HttpStatus.NOT_FOUND);
        }

    }

    public RoleEntity findRoleById(Long id){
        return roleRepository.findById(id).orElse(null);
    }
}
