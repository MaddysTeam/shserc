package com.dianda.shserc.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckIdCard implements ConstraintValidator<ICheckIdCard,String> {
	
	@Override
	public boolean isValid( String s , ConstraintValidatorContext constraintValidatorContext ) {
		return false;
	}
}
