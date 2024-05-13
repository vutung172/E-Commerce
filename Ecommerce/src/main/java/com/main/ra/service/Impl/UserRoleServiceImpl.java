package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.model.Enum.RoleType;
import com.main.ra.model.entity.RoleEntity;
import com.main.ra.model.entity.UserEntity;
import com.main.ra.model.entity.UserRoleEntity;
import com.main.ra.repository.RoleRepository;
import com.main.ra.repository.UserRepository;
import com.main.ra.repository.UserRoleRepository;
import com.main.ra.service.UserRoleService;
import com.main.ra.util.MessageLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MessageLoader loader;

    public UserRoleEntity addUserRole(Long userId, Long roleId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        RoleEntity role = roleRepository.findById(roleId).orElse(null);
        if (user != null) {
            if (role != null) {
                UserRoleEntity userRole = new UserRoleEntity(user, role);
                return userRoleRepository.save(userRole);
            } else {
                throw new BaseException("exception.RoleTypeNotFound", HttpStatus.NOT_FOUND);
            }
        } else {
            throw new BaseException("exception.UserNotFound", HttpStatus.NOT_FOUND);
        }
    }

    public List<UserRoleEntity> updateUserRole(Long userId, Long roleId) {
        List<UserRoleEntity> userRoles = userRoleRepository.findAllByUserId(userId);
        RoleEntity role = roleRepository.findById(roleId).orElse(null);
        if (!userRoles.isEmpty()) {
            if (role != null) {
                if (userRoles.stream().noneMatch(ur -> ur.getRoleId().equals(roleId))) {
                    userRoles.add(addUserRole(userId, roleId));
                } else {
                    userRoles.stream()
                            .filter(ur -> ur.getRoleId().equals(roleId))
                            .filter(ur -> !ur.getStatus())
                            .forEach(ur -> ur.setStatus(true));
                }
            } else {
                throw new BaseException("exception.RoleTypeNotFound", HttpStatus.NOT_FOUND);
            }
        } else {
            throw new BaseException("exception.RoleTypeNotFound", HttpStatus.NOT_FOUND);
        }
        return userRoleRepository.saveAll(userRoles);
    }

    public boolean deleteRole(Long userId, Long roleId) {
        List<UserRoleEntity> userRoles = userRoleRepository.findAllByUserId(userId);
        RoleEntity role = roleRepository.findById(roleId).orElse(null);
        if (!userRoles.isEmpty()) {
            if (role != null) {
                userRoles.stream()
                        .filter(ur -> ur.getRoleId().equals(roleId))
                        .filter(UserRoleEntity::getStatus)
                        .forEach(ur -> ur.setStatus(false));
            } else {
                throw new BaseException("exception.RoleTypeNotFound", HttpStatus.NOT_FOUND);
            }
        } else {
            throw new BaseException("exception.RoleTypeNotFound", HttpStatus.NOT_FOUND);
        }
        return userRoleRepository.saveAll(userRoles).stream()
                .filter(ur -> ur.getRoleId().equals(roleId))
                .noneMatch(UserRoleEntity::getStatus);
    }
}
