package com.main.ra.repository;

import com.main.ra.model.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity,Long> {
    List<ShoppingCartEntity> findAllByUserId(Long id);

    Optional<ShoppingCartEntity> findShoppingCartEntitiesByUserIdAndProductId(Long userId, Long productId);
}
