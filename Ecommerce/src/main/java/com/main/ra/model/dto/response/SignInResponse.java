package com.main.ra.model.dto.response;

import com.main.ra.model.Enum.RoleType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {
    private Long id;
    private String fullName;
    private String type;
    private String accessToken;
    private List<RoleType> roles = new ArrayList<>();
}
