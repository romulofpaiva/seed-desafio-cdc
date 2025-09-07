package com.desafio.cdc.constraintvalidators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = { ExistsIdValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsId {

	String message() default "{com.desafio.cdc.constraintvalidators.existsid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String domainAttribute();

	Class<?> domainClass();

}
