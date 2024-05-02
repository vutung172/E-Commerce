package com.main.ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_carts",schema = "ecommerce")
public class ShoppingCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

    @Min(value = 1, message = "{must at least 1}")
    @Column(name = "order_quantity")
    private Integer orderQuantity;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = ProductEntity.class)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}