package com.main.ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(OrderDetailIdEntity.class)
@Table(name = "order_details",schema = "ecommerce")
public class OrderDetailEntity {
    @Id
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Size(max = 100, message = "message.Max-Length-100")
    @Column(name = "name")
    private String name;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Min(value = 1, message = "{message.Min-Qty}")
    @Column(name = "order_quantity")
    private Integer orderQuantity;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",referencedColumnName = "order_id",insertable = false, updatable = false)
    private OrderEntity order;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",referencedColumnName = "product_id",insertable = false, updatable = false)
    private ProductEntity product;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity1;

    public OrderDetailEntity(OrderEntity order, ProductEntity product) {
        this.orderId = order.getId();
        this.productId = product.getId();
        this.order = order;
        this.product = product;
    }
}