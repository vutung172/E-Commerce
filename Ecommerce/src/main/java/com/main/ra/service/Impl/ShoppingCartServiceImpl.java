package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.model.dto.CartDto;
import com.main.ra.model.dto.response.DataResponse;
import com.main.ra.model.entity.ProductEntity;
import com.main.ra.model.entity.ShoppingCartEntity;
import com.main.ra.model.entity.UserEntity;
import com.main.ra.repository.ProductRepository;
import com.main.ra.repository.ShoppingCartRepository;
import com.main.ra.repository.UserRepository;
import com.main.ra.service.BaseService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShoppingCartServiceImpl implements BaseService {
    @Autowired
    private ShoppingCartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MapperUtilServiceImpl mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartEntity.class);

    public List<ShoppingCartEntity> findByUserId(Long id) {
        return cartRepository.findAllByUserId(id);
    }


    public ShoppingCartEntity addToCart(Long userId, Long productId, Integer quantity) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            ProductEntity productEntity = productRepository.findById(productId).orElse(null);
            if (productEntity != null) {
                List<ShoppingCartEntity> carts = cartRepository.findAllByUserId(userId);
                carts.stream()
                        .filter(c -> c.getProductId().equals(productId))
                        .findFirst()
                        .ifPresentOrElse(
                                c -> c.setOrderQuantity(quantity),
                                () -> carts.add(ShoppingCartEntity.builder()
                                                .userId(userId)
                                                .productId(productId)
                                                .orderQuantity(quantity)
                                                .build())
                        );
                return cartRepository.saveAll(carts).stream().filter(c -> c.getProductId().equals(productId)).findFirst().orElse(null);
            } else {
                throw new BaseException("exception.ProductNotFound", HttpStatus.NOT_FOUND);
            }
        } else {
            throw new BaseException("exception.UserNotFound", HttpStatus.NOT_FOUND);
        }

    }

    public boolean deleteByProductId(Long userId,Long productId){
        List<ShoppingCartEntity> cartList = cartRepository.findAllByUserId(userId);
        if (!cartList.isEmpty()){
            cartList.stream()
                    .filter(c -> c.getProductId().equals(productId))
                    .forEach(c -> cartRepository.delete(c));
            return true;
        } else {
            throw new BaseException("exception.CartNotFound",HttpStatus.NOT_FOUND);
        }
    }

    public boolean deleteByUserId(Long userId){
        List<ShoppingCartEntity> cartList = cartRepository.findAllByUserId(userId);
        if (!cartList.isEmpty()){
            cartRepository.deleteAll();
            return true;
        } else {
            throw new BaseException("exception.CartNotFound",HttpStatus.NOT_FOUND);
        }
    }

    public ShoppingCartEntity findByProductId(Long userId,Long productId){
        return cartRepository.findShoppingCartEntitiesByUserIdAndProductId(userId,productId).orElse(null);
    }
}
