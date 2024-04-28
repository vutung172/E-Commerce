package com.main.ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.Generated;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", schema = "ecommerce")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @NotNull(message = "{message.NotNull}")
    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "email")
    private String email;

    @NotNull(message = "{message.NotNull}")
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Generated
    @ColumnDefault(value = "true")
    @Column(name = "status")
    private Boolean status;

    @NotNull(message = "{message.NotNull}")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "phone",unique = true, length = 15)
    private String phone;

    @NotNull(message = "{message.NotNull}")
    @Column(name = "address", nullable = false)
    private String address;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/mm/yyyy")
    @Column(name = "created_at")
    private LocalDate createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/mm/yyyy")
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "user")
    private List<AddressEntity> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ShoppingCartEntity> shoppingCarts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserRoleEntity> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<WishListEntity> wishLists = new ArrayList<>();

}