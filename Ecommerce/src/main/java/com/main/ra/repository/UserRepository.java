package com.main.ra.repository;

import com.main.ra.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserEntitiesByUserName(String userName);
    Optional<UserEntity> findUserEntitiesByUserNameAndPassword(String userName, String password);
    Optional<UserEntity> findUserEntitiesByPhone(String phoneNumber);
}
