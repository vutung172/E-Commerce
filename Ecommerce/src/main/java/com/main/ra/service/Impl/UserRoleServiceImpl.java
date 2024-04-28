package com.main.ra.service.Impl;

import com.main.ra.exception.DatabaseException;
import com.main.ra.model.entity.RoleEntity;
import com.main.ra.model.entity.UserEntity;
import com.main.ra.model.entity.UserRoleEntity;
import com.main.ra.repository.UserRoleRepository;
import com.main.ra.service.UserRoleService;
import com.main.ra.util.MessageLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private MessageLoader loader;

    public UserRoleEntity addUserRole(UserEntity user, RoleEntity role){
        try {
            UserRoleEntity userRole = new UserRoleEntity(user, role);
            return userRoleRepository.save(userRole);
        } catch (RuntimeException re){
            throw new DatabaseException(re,"exception.database.AddUserRoleFailure");
        }
    }
}
