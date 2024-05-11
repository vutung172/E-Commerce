package com.main.ra.model.entity;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.main.ra.model.Enum.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GeneratedColumn;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.factory.internal.UUIDGenerationTypeStrategy;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders", schema = "ecommerce")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "serial_number", insertable = false)
    private String serialNumber;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "total_price")
    private Double totalPrice;

    @Enumerated(value = EnumType.STRING)
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

    @Column(name = "created_at", insertable = false)
    private LocalDate createdAt;

    @Column(name = "received_at", insertable = false)
    private LocalDate receivedAt;

    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    private UserEntity user;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetailEntity> orderDetails = new LinkedHashSet<>();

}