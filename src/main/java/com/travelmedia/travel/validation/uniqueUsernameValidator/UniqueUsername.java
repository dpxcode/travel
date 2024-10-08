package com.travelmedia.travel.validation.uniqueUsernameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public
@interface UniqueUsername {
    String message() default "username is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

