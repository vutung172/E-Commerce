package com.main.ra.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.main.ra.model.Enum.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class OrderDto {
    private String serialNumber;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "")
    private Double totalPrice;
    private OrderStatus status;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate receivedAt;
}
