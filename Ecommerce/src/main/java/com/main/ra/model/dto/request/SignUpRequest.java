package com.main.ra.model.dto.request;

import com.main.ra.model.Enum.RoleType;
import com.main.ra.validator.PasswordConfirm;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PasswordConfirm.List(
        @PasswordConfirm(field = "confirmPassword", fieldConfirm = "password", message = "{exception.PasswordNotSame}")
)
public class SignUpRequest {
    @Length(min = 6, message = "{message.Min-Length-6}")
    @Length(max = 100, message = "{message.Max-Length-100}")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "{message.UserNameFmt}")
    @NotNull(message = "{message.NotNull}")
    private String userName;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\\.[a-z]+$", message = "{message.EmailFmt}")
    private String email;

    @Size(max = 100, message = "{message.Max-Length-100}")
    @NotNull(message = "{message.NotNull}")
    private String fullName;

    private Boolean status;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @NotNull(message = "{message.NotNull}")
    private String password;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @NotNull(message = "{message.NotNull}")
    private String confirmPassword;

    @Size(max = 255, message = "{message.Max-Length-255}")
    private String avatar;

    @Size(max = 15, message = "{message.Max-Length-15}")
    @Pattern(regexp = "^0[1-9]+[0-9]{8}$", message = "{message.PhoneFmt}")
    private String phone;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @NotNull(message = "{message.NotNull}")
    private String address;

    @Enumerated(EnumType.STRING)
    List<RoleType> roles;
}
