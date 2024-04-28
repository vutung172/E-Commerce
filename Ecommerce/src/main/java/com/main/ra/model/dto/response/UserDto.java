package com.main.ra.model.dto.response;

import com.main.ra.model.Enum.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private String fullName;
    private Boolean status;
    private String password;
    private String avatar;
    private String phone;
    private String address;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @Enumerated(EnumType.STRING)
    private List<RoleType> roles;
}
