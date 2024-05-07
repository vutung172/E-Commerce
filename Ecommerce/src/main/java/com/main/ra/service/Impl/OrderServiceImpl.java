package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.model.Enum.OrderStatus;
import com.main.ra.model.dto.CartDto;
import com.main.ra.model.entity.*;
import com.main.ra.repository.AddressRepository;
import com.main.ra.repository.OrderRepository;
import com.main.ra.repository.ShoppingCartRepository;
import com.main.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ShoppingCartRepository cartRepository;
    @Autowired
    private OrderDetailServiceImpl orderDetailService;
    @Autowired
    private MapperUtilServiceImpl mapper;

    @Transactional
    public OrderEntity checkoutAllInCart(Long userId, Long addressId, List<ShoppingCartEntity> cartList){
        UserEntity user = userRepository.findById(userId).orElse(null);
        AddressEntity address = addressRepository.findById(addressId).orElse(null);
        if (user != null && address != null){
            OrderEntity order = add(userId);
            if (order != null){
                List<OrderDetailEntity> orderDetailList = cartList.stream()
                        .map(c -> orderDetailService.add(order.getId(),c.getProductId(),c.getOrderQuantity()))
                        .toList();
                Double totalPrice = orderDetailList.stream()
                        .mapToDouble(od -> ( od.getProduct().getUnitPrice() * od.getOrderQuantity()))
                        .sum();
                order.setStatus(OrderStatus.WAITING);
                order.setReceiveName(address.getReceiveName());
                order.setReceiveAddress(address.getFullAddress());
                order.setReceivePhone(address.getPhone());
                order.setTotalPrice(totalPrice);
                return update(order.getId(), order);
            } else {
                throw new BaseException("exception.OrderNotFound",HttpStatus.NOT_FOUND);
            }
        } else {
            throw new BaseException("exception.UserNotFound", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public OrderEntity add(Long userId){
        OrderEntity order = OrderEntity.builder()
                .userId(userId)
                .build();
        return orderRepository.save(order);
    }

    @Transactional
    public OrderEntity update(Long orderId, OrderEntity newOrder){
        OrderEntity order = orderRepository.findById(orderId).orElse(null);
        if (order != null){
            mapper.updateToEntity(newOrder,order);
            return orderRepository.save(order);
        } else {
            throw new BaseException("exception.OrderNotFound",HttpStatus.NOT_FOUND);
        }
    }


    public OrderEntity findByOrderId(Long orderId){
        return orderRepository.findById(orderId).orElse(null);
    }
}
