package com.main.ra.model.entity;

import com.main.ra.model.Enum.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles",schema = "ecommerce")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleType roleName;
}