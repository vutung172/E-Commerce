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
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MessageLoader loader;

    public UserRoleEntity addUserRole(Long userId, Long roleId){
        try {
            UserEntity user = userRepository.findById(userId).orElse(null);
            RoleEntity role = roleRepository.findById(roleId).orElse(null);
            if (user != null && role != null){
                UserRoleEntity userRole = new UserRoleEntity(user, role);
                return userRoleRepository.save(userRole);
            } else {
                throw new BaseException("exception.UserNotFound",HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException re){
            throw new BaseException("exception.database.AddUserRoleFailure", HttpStatus.NOT_FOUND);
        }
    }

    public List<UserRoleEntity> updateUserRole(Long userId, Long roleId){
        try {
            List<UserRoleEntity> userRoles = userRoleRepository.findAllByUserId(userId);
            if (userRoles.stream().noneMatch(ur -> ur.getRoleId().equals(roleId))){
                userRoles.add(addUserRole(userId,roleId));
            } else {
                userRoles.stream()
                        .filter(ur -> ur.getRoleId().equals(roleId))
                        .filter(ur -> !ur.getStatus())
                        .forEach(ur -> ur.setStatus(true));
            }
            return userRoleRepository.saveAll(userRoles);
        } catch (RuntimeException re){
            throw new BaseException("exception.database.AddUserRoleFailure", HttpStatus.NOT_FOUND);
        }
    }

    public boolean deleteRole(Long userId, Long roleId){
        try {
            List<UserRoleEntity> userRoles = userRoleRepository.findAllByUserId(userId);
            userRoles.stream()
                    .filter(ur -> ur.getRoleId().equals(roleId))
                    .filter(UserRoleEntity::getStatus)
                    .forEach(ur -> ur.setStatus(false));
            return userRoleRepository.saveAll(userRoles).stream()
                    .filter(ur -> ur.getRoleId().equals(roleId))
                    .noneMatch(UserRoleEntity::getStatus);
        } catch (RuntimeException re){
            throw new BaseException("exception.database.AddUserRoleFailure", HttpStatus.NOT_FOUND);
        }
    }
}
