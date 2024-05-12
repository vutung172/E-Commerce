package com.main.ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.LocalDate;
import java.util.List;

@Builder
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

    @ColumnDefault(value = "b'1'")
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

    @Generated(event = {EventType.INSERT})
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Generated(event = {EventType.UPDATE})
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "user")
    private List<AddressEntity> addresses;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;

    @OneToMany(mappedBy = "user")
    private List<ShoppingCartEntity> shoppingCarts;

    @OneToMany(mappedBy = "user")
    private List<UserRoleEntity> roles;

    @OneToMany(mappedBy = "user")
    private List<WishListEntity> wishLists;

}