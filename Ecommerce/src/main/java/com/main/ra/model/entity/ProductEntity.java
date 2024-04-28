package com.main.ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
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
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @UUID
    @Column(name = "sku", length = 100)
    private String sku;

    @Size(max = 100)
    @NotNull
    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetailEntity> orderDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ShoppingCartEntity> shoppingCarts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<WishListEntity> wishListEntities = new LinkedHashSet<>();

}