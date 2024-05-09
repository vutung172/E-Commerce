package com.main.ra.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordConfirm {
    String message() default "Password invalid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String field();
    String fieldConfirm();

    @Target({ ElementType.FIELD})
    @Retention(RUNTIME)
    @interface ConfirmField{

    }

    @Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        PasswordConfirm[] value();
    }

}
