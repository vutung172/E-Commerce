package com.main.ra.repository;

import com.main.ra.model.entity.ProductEntity;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findAllByProductNameIsLikeIgnoreCaseOrDescriptionIsLikeIgnoreCase(String name,String description);
    ProductEntity findProductEntitiesByProductName(String name);
}
