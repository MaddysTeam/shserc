package com.dianda.shserc.validators;

import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.basic.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckNotNulI implements ConstraintValidator<NotNull,Object> {
	
	@Override
	public boolean isValid( Object o , ConstraintValidatorContext constraintValidatorContext ) {
		return !ObjectUtil.isNull ( o ) ;
	}
	
}


