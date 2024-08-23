package com.travelmedia.travel.validation.uniqueEmailValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public
@interface UniqueEmail {
    String message() default "Email is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

