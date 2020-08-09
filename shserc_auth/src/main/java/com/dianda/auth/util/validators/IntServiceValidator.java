package com.dianda.auth.util.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntServiceValidator implements ConstraintValidator<IServiceValidation, Integer> {
	
	@Override
	public void initialize(IServiceValidation constraintAnnotation) {
	}
	
	@Override
	public boolean isValid( Integer s, ConstraintValidatorContext constraintValidatorContext) {
		return s > 0;
	}
	
}
