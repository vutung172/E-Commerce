package com.main.ra.repository;

import com.main.ra.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
    Optional<AddressEntity> findAddressEntitiesByUserIdAndId(Long userId,Long addressId);

    List<AddressEntity> findAllByUserId(Long userId);
}
