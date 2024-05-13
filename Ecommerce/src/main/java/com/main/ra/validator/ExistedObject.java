package com.main.ra.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ExistedObjectValidator.class)
public @interface ExistedObject {
    String message() default "Password invalid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
