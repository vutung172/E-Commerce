package com.main.ra.validator;

import com.main.ra.model.Enum.OrderStatus;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = OrderStatusValidator.class)
public @interface OrderStatusValidate {
    String message() default "Password invalid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
