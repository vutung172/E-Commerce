package com.main.ra.model.entity;

import com.main.ra.model.Enum.OrderStatus;
import com.main.ra.validator.OrderStatusValidate;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "ecommerce")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Generated(event = {EventType.INSERT,EventType.UPDATE})
    @Column(name = "serial_number", length = 36)
    private String serialNumber;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "total_price")
    private Double totalPrice;

    @Enumerated(value = EnumType.STRING)
    /*@OrderStatusValidate(message = "{exception.OrderStatus.NotMatch}")*/
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "note", length = 100)
    private String note;

    @Column(name = "receive_name", length = 100)
    private String receiveName;

    @Column(name = "receive_address")
    private String receiveAddress;

    @Column(name = "receive_phone", length = 15)
    private String receivePhone;

    @Generated(event = {EventType.INSERT,EventType.UPDATE})
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Generated(event = {EventType.INSERT,EventType.UPDATE})
    @Column(name = "received_at")
    private LocalDate receivedAt;

    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    private UserEntity user;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetailEntity> orderDetails = new LinkedHashSet<>();

}