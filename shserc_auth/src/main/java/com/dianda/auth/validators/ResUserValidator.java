package com.dianda.auth.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class ResUserValidator implements ConstraintValidator<Annotation,String> {

	@Override
	public void initialize(Annotation constraintAnnotation) {

	}

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		return false;
	}
}
