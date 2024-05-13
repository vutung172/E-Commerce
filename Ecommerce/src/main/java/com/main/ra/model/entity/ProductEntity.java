package com.main.ra.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.generator.EventType;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NamedEntityGraph(name = "graph.Product.cart", attributeNodes = {
        @NamedAttributeNode(value = "shoppingCarts")
})
@Table(name = "products",schema = "ecommerce")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true)
    private Long id;

    @Generated(event = {EventType.INSERT,EventType.UPDATE})
    @Size(max = 100, message = "{message.Max-Length-100}")
    @Column(name = "sku", unique = true, insertable = false, updatable = false)
    private String sku;

    @Size(max = 100)
    @NotNull(message = "{message.NotNull}")
    @Column(name = "product_name", unique = true)
    private String productName;

    @Column(name = "description")
    private String description;

    @NumberFormat(pattern = "#,##0.00",style = NumberFormat.Style.NUMBER)
    @Min(value = 0, message = "{message.Min-Price}")
    @Column(name = "unit_price")
    private Double unitPrice;

    @Min(value = 1, message = "{message.StockQty}")
    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @Column(name = "image")
    private String image;

    @Column(name = "category_id")
    private Long categoryId;

    /*@Generated(event = {EventType.INSERT})*/
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDate createdAt;

    /*@Generated(event = {EventType.UPDATE})*/
    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDate updatedAt;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetailEntity> orderDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ShoppingCartEntity> shoppingCarts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<WishListEntity> wishListEntities = new LinkedHashSet<>();

}