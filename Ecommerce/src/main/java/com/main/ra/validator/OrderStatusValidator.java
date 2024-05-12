package com.main.ra.validator;

import com.main.ra.model.Enum.OrderStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class OrderStatusValidator implements ConstraintValidator<OrderStatusValidate, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return Arrays.stream(OrderStatus.values()).map(Enum::toString).anyMatch(os -> os.equals(value));
    }
}
