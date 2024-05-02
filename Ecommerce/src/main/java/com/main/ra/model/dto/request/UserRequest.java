package com.main.ra.model.dto.request;

import jakarta.persistence.Column;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@MultipartConfig(location = "/temporary", maxRequestSize = 50)
public class UserRequest {
    @NotNull(message = "{message.NotNull}")
    private String fullName;

    @Size(max = 255)
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\\.[a-z]+$", message = "{message.EmailFmt}")
    private String email;

    @Size(max = 15)
    @Pattern(regexp = "^0[1-9]+[0-9]{8}$", message = "{message.PhoneFmt}")
    private String phone;

    @NotNull(message = "{message.NotNull}")
    private String address;

    private MultipartFile file;
}
