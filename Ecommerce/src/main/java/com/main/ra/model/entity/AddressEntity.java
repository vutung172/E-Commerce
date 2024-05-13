package com.main.ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address", schema = "ecommerce")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @Column(name = "full_address")
    private String fullAddress;

    @Size(max = 15,message = "{message.Max-Length-15}")
    @Column(name = "phone")
    private String phone;

    @Size(max = 50, message = "{message.Max-Length-50}")
    @Column(name = "receive_name")
    private String receiveName;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

}