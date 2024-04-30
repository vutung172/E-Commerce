package com.main.ra.model.entity;

import com.main.ra.model.Enum.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders",schema = "ecommerce")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Size(max = 100)
    @Column(name = "note", length = 100)
    private String note;

    @Size(max = 100)
    @Column(name = "receive_name", length = 100)
    private String receiveName;

    @Size(max = 255)
    @Column(name = "receive_address")
    private String receiveAddress;

    @Size(max = 15)
    @Column(name = "receive_phone", length = 15)
    private String receivePhone;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "received_at")
    private LocalDate receivedAt;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetailEntity> orderDetails = new LinkedHashSet<>();

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

}