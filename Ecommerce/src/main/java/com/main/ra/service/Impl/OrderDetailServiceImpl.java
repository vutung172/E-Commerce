package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.model.entity.OrderDetailEntity;
import com.main.ra.model.entity.OrderEntity;
import com.main.ra.model.entity.ProductEntity;
import com.main.ra.repository.OrderDetailRepository;
import com.main.ra.repository.OrderRepository;
import com.main.ra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderDetailServiceImpl {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public OrderDetailEntity add(Long orderId, Long productId, Integer quantity){
        ProductEntity product = productRepository.findById(productId).orElse(null);
        OrderEntity order = orderRepository.findById(orderId).orElse(null);
        if (product != null && order != null){
            OrderDetailEntity orderDetail = OrderDetailEntity.builder()
                    .orderId(orderId)
                    .productId(productId)
                    .name(product.getProductName())
                    .unitPrice(product.getUnitPrice())
                    .orderQuantity(quantity)
                    .order(order)
                    .product(product)
                    .build();
            return orderDetailRepository.save(orderDetail);
        } else {
            throw  new BaseException("exception.ProductNotFound", HttpStatus.NOT_FOUND);
        }
    }

}
