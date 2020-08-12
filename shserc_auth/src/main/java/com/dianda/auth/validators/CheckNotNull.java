package com.dianda.auth.validators;

import com.dianda.auth.util.basic.ObjectUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckNotNull implements ConstraintValidator<ICheckNotNull,Object> {
	
	@Override
	public boolean isValid( Object o , ConstraintValidatorContext constraintValidatorContext ) {
		return !ObjectUtil.isNull ( o );
	}
	
}
