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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Size(max = 255)
    @Column(name = "full_address")
    private String fullAddress;

    @Size(max = 15)
    @Column(name = "phone", length = 15)
    private String phone;

    @Size(max = 50)
    @Column(name = "receive_name", length = 50)
    private String receiveName;

}