package com.main.ra.model.dto.request;

import com.main.ra.model.Enum.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @Length(min = 6, message = "more than 6 characters")
    @Length(max = 100, message = "less than 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "{message.UserNameFmt}")
    @NotNull(message = "{message.NotNull}")
    private String userName;

    @Size(max = 255)
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\\.[a-z]+$", message = "{message.EmailFmt}")
    private String email;

    @Size(max = 100)
    @NotNull(message = "{message.NotNull}")
    private String fullName;

    private Boolean status;

    @Size(max = 255)
    @NotNull(message = "{message.NotNull}")
    private String password;

    @Size(max = 255)
    @NotNull(message = "{message.NotNull}")
    private String confirmPassword;

    @Size(max = 255)
    private String avatar;

    @Size(max = 15)
    @Pattern(regexp = "^0[1-9]+[0-9]{8}$", message = "{message.PhoneFmt}")
    private String phone;

    @Size(max = 255)
    @NotNull(message = "{message.NotNull}")
    private String address;

    @Enumerated(EnumType.STRING)
    List<RoleType> roles;

    @AssertTrue(message = "{EX-03-003}")
    public boolean confirmPassword(){
        return password.equals(confirmPassword);
    }
}
