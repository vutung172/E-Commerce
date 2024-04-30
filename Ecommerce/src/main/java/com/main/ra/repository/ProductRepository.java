package com.main.ra.repository;

import com.main.ra.model.entity.ProductEntity;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findAllByProductNameOrDescription(String name,String description);
}
