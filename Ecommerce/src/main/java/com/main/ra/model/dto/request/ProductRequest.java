package com.main.ra.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @NotNull(message = "{message.NotNull}")
    @Size(max = 100, message = "{message.Min-Length-100}")
    private String productName;
    private String description;
    @Min(value = 0,message = "{message.Min-Price}")
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.NUMBER)
    private Double unitPrice;
    @Min(value = 1, message = "{message.Min-Qty}")
    @NumberFormat(pattern = "#,##0",style = NumberFormat.Style.NUMBER)
    private Integer stockQuantity;
    private MultipartFile image;
    private Long categoryId;
}
