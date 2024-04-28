package com.main.ra.repository;

import com.main.ra.model.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishListEntity,Long> {
}
