package com.main.ra.model.dto;

import com.main.ra.model.Enum.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userName;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String avatar;
}
