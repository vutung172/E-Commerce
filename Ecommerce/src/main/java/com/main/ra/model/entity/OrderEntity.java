package com.main.ra.model.entity;

import com.main.ra.model.Enum.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.id.uuid.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders",schema = "ecommerce")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(name = "serial_number", length = 32, insertable = false)
    private UUID serialNumber;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NumberFormat(pattern = "#,##0.00",style = NumberFormat.Style.NUMBER)
    @Column(name = "total_price")
    private Double totalPrice;

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

    @Column(name = "created_at",insertable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "received_at",insertable = false, updatable = false)
    private LocalDate receivedAt;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetailEntity> orderDetails;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

}