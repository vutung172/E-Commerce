package com.main.ra.controller.admin;

import com.main.ra.model.dto.response.DataResponse;
import com.main.ra.model.entity.RoleEntity;
import com.main.ra.service.Impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api.myservice.com/v1/admin/roles")
public class RoleApi {
    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping
    public ResponseEntity getRoles(){
        List<RoleEntity> roles = roleService.findAll();
        DataResponse<RoleEntity> rolesData = new DataResponse<>();
        rolesData.setData(roles);
        return ResponseEntity.ok(rolesData);
    }
}
