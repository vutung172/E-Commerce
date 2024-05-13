package com.main.ra.model.entity;

import com.main.ra.model.Enum.OrderStatus;
import com.main.ra.validator.OrderStatusValidate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.SourceType;
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

    @Generated(event = {EventType.INSERT})
    @Size(max = 100,message = "{message.Max-Length-100}")
    @Column(name = "serial_number")
    private String serialNumber;

    @NotNull(message = "{message.NotNull}")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Min(value = 0, message = "{message.Min-Price}")
    @Column(name = "total_price")
    private Double totalPrice;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Size(max = 100, message = "{message.Max-Length-100}")
    @Column(name = "note")
    private String note;

    @Size(max = 100, message = "{message.Max-Length-100}")
    @Column(name = "receive_name")
    private String receiveName;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @Column(name = "receive_address")
    private String receiveAddress;

    @Size(max = 15, message = "{message.Max-Length-15}")
    @Column(name = "receive_phone")
    private String receivePhone;

    @Generated(event = {EventType.INSERT})
    @Column(name = "created_at",insertable = false,updatable = false)
    private LocalDate createdAt;

    @Generated(event = {EventType.INSERT})
    @Column(name = "received_at",insertable = false,updatable = false)
    private LocalDate receivedAt;

    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    private UserEntity user;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetailEntity> orderDetails = new LinkedHashSet<>();

}