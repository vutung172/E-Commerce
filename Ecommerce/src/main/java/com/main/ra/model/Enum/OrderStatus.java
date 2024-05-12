package com.main.ra.model.Enum;

import com.main.ra.validator.OrderStatusValidate;

import javax.annotation.processing.SupportedAnnotationTypes;

@SupportedAnnotationTypes(value = "OrderStatusValidate")
public enum OrderStatus {
    // Đơn hàng mới chờ xác nhận
    WAITING,

    //Đã xác nhận
    CONFIRM,

    //Đang giao hàng
    DELIVERY,

    //Đã giao hàng
    SUCCESS,

    //Đã hủy đơn
    CANCEL,

    //Bị từ chối
    DENIED
}
