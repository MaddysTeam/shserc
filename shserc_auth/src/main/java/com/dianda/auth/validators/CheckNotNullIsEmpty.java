package com.dianda.auth.validators;

import com.dianda.auth.util.basic.ObjectUtil;
import com.dianda.auth.util.basic.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckNotNullIsEmpty implements ConstraintValidator<ICheckNotNullOrEmpty,Object> {
	
	@Override
	public boolean isValid( Object o , ConstraintValidatorContext constraintValidatorContext ) {
		return !ObjectUtil.isNull ( o )  && ! StringUtil.IsNullOrEmpty ( o.toString () );
	}
	
}
