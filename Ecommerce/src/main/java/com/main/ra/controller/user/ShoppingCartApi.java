package com.main.ra.controller.user;


import com.main.ra.exception.BaseException;
import com.main.ra.model.dto.CartDto;
import com.main.ra.model.dto.OrderDto;
import com.main.ra.model.dto.request.AddressRequest;
import com.main.ra.model.dto.request.PayloadRequest;
import com.main.ra.model.dto.response.DataResponse;
import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.model.entity.OrderEntity;
import com.main.ra.model.entity.ShoppingCartEntity;
import com.main.ra.repository.OrderRepository;
import com.main.ra.service.Impl.MapperUtilServiceImpl;
import com.main.ra.service.Impl.OrderServiceImpl;
import com.main.ra.service.Impl.ShoppingCartServiceImpl;
import com.main.ra.util.MessageLoader;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api.myservice.com/v1/user/shopping-cart")
public class ShoppingCartApi {
    @Autowired
    private ShoppingCartServiceImpl cartService;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private MapperUtilServiceImpl mapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MessageLoader loader;

    @GetMapping
    public ResponseEntity listAllProductsInCart(
            @RequestHeader Long userId
    ){
        List<ShoppingCartEntity> cartEntities = cartService.findByUserId(userId);
        List<CartDto> cartDtoList = cartEntities.stream()
                .map(ce -> mapper.convertCartEntityToCartDto(ce))
                .toList();
        DataResponse<CartDto> cart = new DataResponse<>(cartDtoList);
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity addProductToCart(
            @RequestHeader Long userId,
            @RequestBody PayloadRequest<String,String> payload
            ){
        ShoppingCartEntity cartEntity = cartService.addToCart(userId,
                Long.valueOf(payload.getPayload().get("productId")),
                Integer.valueOf(payload.getPayload().get("quantity")));
        if (cartEntity != null){
            CartDto cartDto = mapper.convertCartEntityToCartDto(cartEntity);
            DataResponse<CartDto> data = new DataResponse<>(Collections.singletonList(cartDto));
            return ResponseEntity.ok(data);
        } else {
            throw new BaseException("failure.AddToCart", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity updateQuantityToCart(
            @RequestHeader Long userId,
            @PathVariable Long productId,
            @RequestBody PayloadRequest<String,String> payload
    ){
        ShoppingCartEntity cart = cartService.updateQuantityToCart(userId,
                productId,
                Integer.valueOf(payload.getPayload().get("quantity")));
        if (cart != null){
            CartDto cartDto = mapper.convertCartEntityToCartDto(cart);
            DataResponse<CartDto> data = new DataResponse<>(Collections.singletonList(cartDto));
            return ResponseEntity.ok(data);
        } else {
            throw new BaseException("failure.UpdateQtyToCart", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity updateQuantityToCart(
            @RequestHeader Long userId,
            @PathVariable Long productId
    ){
        if (cartService.deleteByProductId(userId,productId)){
            return ResponseEntity.ok(new MessageResponse(loader.getMessage("success.DeleteCart")));
        } else {
            throw new BaseException("failure.DeleteProductFromCart", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping
    public ResponseEntity updateQuantityToCart(
            @RequestHeader Long userId
    ){
        if (cartService.deleteByUserId(userId)){
            return ResponseEntity.ok(new MessageResponse(loader.getMessage("success.DeleteCart")));
        } else {
            throw new BaseException("failure.DeleteProductFromCart", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(
            @RequestHeader Long userId,
            @RequestBody @Valid AddressRequest request
    ){
        List<ShoppingCartEntity> cartEntities = cartService.findByUserId(userId);
        OrderEntity order = orderService.checkoutAllInCart(userId, request.getAddressId(), cartEntities);
        if (order != null){
            OrderDto orderDto = mapper.convertEntityToDTO(order, OrderDto.class);
            return ResponseEntity.ok(new DataResponse<>(Collections.singletonList(orderDto)));
        } else {
            throw new BaseException("exception.OrderCreatedFailure",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<OrderDto>> getOrderById(
            @PathVariable Long id
    ){
        OrderEntity order = orderService.findByOrderId(id);
        if (order != null){
            OrderDto orderDto = mapper.convertEntityToDTO(order, OrderDto.class);
            return ResponseEntity.ok(new DataResponse<>(Collections.singletonList(orderDto)));
        } else {
            throw new BaseException("exception.OrderCreatedFailure",HttpStatus.NOT_FOUND);
        }
    }
}
