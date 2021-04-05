package com.dianda.common.validators;

import com.dianda.common.util.basic.ObjectUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckNotNulI implements ConstraintValidator<NotNull,Object> {
	
	@Override
	public boolean isValid( Object o , ConstraintValidatorContext constraintValidatorContext ) {
		return !ObjectUtil.isNull ( o ) ;
	}
	
}


