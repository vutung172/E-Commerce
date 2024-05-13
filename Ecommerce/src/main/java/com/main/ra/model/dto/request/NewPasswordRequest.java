package com.main.ra.model.dto.request;

import com.main.ra.validator.PasswordConfirm;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PasswordConfirm.List(
        @PasswordConfirm(field = "confirmPassword", fieldConfirm = "newPassword", message = "{exception.PasswordNotSame}")
)
public class NewPasswordRequest {
    @Size(max = 255, message = "{message.Max-Length-255}")
    @NotNull(message = "{message.NotNull}")
    private String oldPassword;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @NotNull(message = "{message.NotNull}")
    private String newPassword;

    @Size(max = 255, message = "{message.Max-Length-255}")
    @NotNull(message = "{message.NotNull}")
    private String confirmPassword;
}
