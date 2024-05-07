package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.model.dto.CartDto;
import com.main.ra.model.dto.UserDto;
import com.main.ra.model.dto.request.UserRequest;
import com.main.ra.model.entity.ProductEntity;
import com.main.ra.model.entity.ShoppingCartEntity;
import com.main.ra.model.entity.UserEntity;
import com.main.ra.service.MapperUtilService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MapperUtilServiceImpl implements MapperUtilService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperUtilServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public <E, D> D convertEntityToDTO(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto); // Using Apache Commons BeanUtils for property copying
            return dto;
        } catch (Exception ex) {
            // Handle exception accordingly
            ex.printStackTrace();
            return null;
        }
    }

    public <D, E> E convertDTOToEntity(D dtoClass, Class<E> entity) {
        try {
            E entityBase = entity.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dtoClass, entityBase); // Using Apache Commons BeanUtils for property copying
            return entityBase;
        } catch (Exception ex) {
            // Handle exception accordingly
            ex.printStackTrace();
            return null;
        }
    }

    public <D, E> E updateToEntity( D dto, E entity) {
        try {
            BeanUtils.copyProperties(dto, entity);
            return entity;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public CartDto convertCartEntityToCartDto(ShoppingCartEntity cartEntity){
        ProductEntity product = entityManager.find(ProductEntity.class,cartEntity.getProductId());
        if (product != null) {
            return CartDto.builder()
                    .id(cartEntity.getId())
                    .productName(product.getProductName())
                    .unitPrice(product.getUnitPrice())
                    .quantity(cartEntity.getOrderQuantity())
                    .build();
        } else {
            throw new BaseException("exception.ProductNotFound", HttpStatus.NOT_FOUND);
        }
    }
}
