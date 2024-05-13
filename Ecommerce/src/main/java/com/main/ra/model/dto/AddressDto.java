package com.main.ra.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Long id;

    @Size(max = 255, message = "{message.Max-Length-255}")
    private String fullAddress;

    @Size(max = 15,message = "{message.Max-Length-15}")
    private String phone;

    @Size(max = 50, message = "{message.Max-Length-50}")
    private String receiveName;
}
