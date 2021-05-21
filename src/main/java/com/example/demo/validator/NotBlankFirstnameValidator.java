package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.web.dto.model.UserDto;

public class NotBlankFirstnameValidator implements ConstraintValidator<NotBlankFirstname, UserDto> {

	@Override
	public boolean isValid(UserDto value, ConstraintValidatorContext context) {
		return ((value.getIdType() != null && !value.getIdType().equals("NIT"))
				&& (value.getFirstName() != null && !value.getFirstName().isEmpty()));
	}
}
