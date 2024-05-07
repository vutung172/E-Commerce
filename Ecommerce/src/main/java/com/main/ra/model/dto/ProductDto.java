package com.main.ra.model.dto;

import lombok.*;
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
    @NumberFormat(pattern = "#,##0.00",style = NumberFormat.Style.NUMBER)
    private BigDecimal unitPrice;
    private String imageUrl;
}
