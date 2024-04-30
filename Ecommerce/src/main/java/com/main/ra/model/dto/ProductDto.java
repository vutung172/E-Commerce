package com.main.ra.model.dto;

import jakarta.validation.constraints.DecimalMax;
import lombok.*;
import org.hibernate.validator.constraints.Currency;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String productName;
    private String description;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal unitPrice;
    private String imageUrl;
}
