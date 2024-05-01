package com.main.ra.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products",schema = "ecommerce")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Size(max = 100)
    @Column(name = "sku", length = 100)
    private String sku;

    @Size(max = 100)
    @NotNull
    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Min(value = 1, message = "{message.StockQty}")
    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetailEntity> orderDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ShoppingCartEntity> shoppingCarts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<WishListEntity> wishListEntities = new LinkedHashSet<>();

}