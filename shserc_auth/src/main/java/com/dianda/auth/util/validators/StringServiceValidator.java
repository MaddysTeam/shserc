package com.dianda.auth.util.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *  @title ServiceValidator
 *  @Description invoke concrete validation step here when service validation been triggered;
 *  @author jimmypoor
 *  @Date 2020/2/8 16:26
 *  @Copyright 2019-2020 
 */

public class StringServiceValidator implements ConstraintValidator<IServiceValidation, String> {
	
	@Override
	public void initialize(IServiceValidation constraintAnnotation) { }
	
	@Override
	public boolean isValid( String s, ConstraintValidatorContext constraintValidatorContext) {
		return !s.isEmpty();
	}
	
}


