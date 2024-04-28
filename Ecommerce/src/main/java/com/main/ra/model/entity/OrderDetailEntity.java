package com.main.ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_details",schema = "ecommerce")
public class OrderDetailEntity {
    @EmbeddedId
    private OrderDetailIdEntity id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "order_quantity")
    private Integer orderQuantity;

}