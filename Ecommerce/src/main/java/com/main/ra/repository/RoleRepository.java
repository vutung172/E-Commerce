package com.main.ra.repository;

import com.main.ra.model.Enum.RoleType;
import com.main.ra.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findRoleEntitiesByRoleName(RoleType roleName);
}
