package com.springboot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PostCodeValidator.class)
public @interface Postcode {
	
	String message() default "Invalid post code";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
