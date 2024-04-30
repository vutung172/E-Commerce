package com.main.ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details",schema = "ecommerce")
public class OrderDetailEntity {
    @EmbeddedId()
    private OrderDetailIdEntity id;

    @Column(name = "order_id", nullable = false)
    private Long orderID;

    @Column(name = "product_id", nullable = false)
    private Long productID;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Size(min = 1, message = "{message.Min-Qty}")
    @Column(name = "order_quantity")
    private Integer orderQuantity;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id",referencedColumnName = "order_id",insertable = false, updatable = false)
    private OrderEntity order;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id",referencedColumnName = "product_id",insertable = false, updatable = false)
    private ProductEntity product;

    public OrderDetailEntity(OrderEntity order, ProductEntity product) {
        this.orderID = order.getId();
        this.productID = product.getId();
        this.order = order;
        this.product = product;
    }
}