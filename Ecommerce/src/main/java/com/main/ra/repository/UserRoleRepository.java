package com.main.ra.repository;

import com.main.ra.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {
    List<UserRoleEntity> findAllByUserId(Long id);
}
