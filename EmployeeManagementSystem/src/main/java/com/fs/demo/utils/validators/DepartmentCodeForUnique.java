package com.fs.demo.utils.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Constraint(validatedBy = DepartmentCodeForUniqueValidator.class)
public @interface DepartmentCodeForUnique {
	String message() default "Invalid department code.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
