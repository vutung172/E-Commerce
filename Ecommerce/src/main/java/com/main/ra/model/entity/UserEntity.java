package com.main.ra.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
    @Length(min = 6, message = "{message.Min-Length-6}")
    @Length(max = 100, message = "{message.Max-Length-100}")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "{message.UserNameFmt}")
    @Column(name = "user_name", unique = true)
    private String userName;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\\.[a-z]+$", message = "{message.EmailFmt}")
    @Column(name = "email")
    private String email;

    @Size(max = 100, message = "{message.Max-Length-100}")
    @NotNull(message = "{message.NotNull}")
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "status", insertable = false)
    private Boolean status;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @NotNull(message = "{message.NotNull}")
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @Column(name = "avatar")
    private String avatar;

    @Size(max = 15, message = "{message.Max-Length-15}")
    @Pattern(regexp = "^0[1-9]+[0-9]{8}$", message = "{message.PhoneFmt}")
    @Column(name = "phone",unique = true)
    private String phone;

    @Size(max = 255, message = "{message.Max-Length-255}")
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