package com.main.ra.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewAddressRequest {
    @Size(max = 50, message = "{message.Max-Length-50}")
    private String receiveName;

    @Size(max = 255, message = "{message.Max-Length-255}")
    private String fullAddress;

    @Size(max = 15,message = "{message.Max-Length-15}")
    @Pattern(regexp = "^0[1-9]+[0-9]{8}$", message = "{message.PhoneFmt}")
    private String phone;


}
