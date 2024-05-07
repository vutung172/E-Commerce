package com.main.ra.model.dto;

import com.main.ra.model.Enum.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

@Getter
@Setter
public class OrderDto {
    private String serialNumber;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double totalPrice;
    private OrderStatus status;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private LocalDate createdAt;
    private LocalDate receivedAt;
}
