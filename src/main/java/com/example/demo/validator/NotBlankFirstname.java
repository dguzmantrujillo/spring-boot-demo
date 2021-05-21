package com.example.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { NotBlankFirstnameValidator.class })
public @interface NotBlankFirstname {

	String message() default "First name is required";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
