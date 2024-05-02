package com.main.ra.service.Impl;

import com.main.ra.model.entity.OrderEntity;
import com.main.ra.repository.AddressRepository;
import com.main.ra.repository.OrderRepository;
import com.main.ra.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ShoppingCartRepository cartRepository;

    /*public OrderEntity checkout(Long userId, Long addressId){

    }

    public BigDecimal totalOrderExpense(){

    }*/

}
