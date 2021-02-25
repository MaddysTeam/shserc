package com.dianda.shserc.validators;

import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.basic.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckNotNullIsEmpty implements ConstraintValidator<ICheckNotNullOrEmpty,Object> {
	
	@Override
	public boolean isValid( Object o , ConstraintValidatorContext constraintValidatorContext ) {
		return !ObjectUtil.isNull ( o )  && ! StringUtil.IsNullOrEmpty ( o.toString () );
	}
	
}


