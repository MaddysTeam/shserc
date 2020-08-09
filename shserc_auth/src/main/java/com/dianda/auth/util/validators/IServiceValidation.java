package com.dianda.auth.util.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  @title IServiceValidation
 *  @Description  validation service for targets like class ,field and methods
 *  @author jimmypoor
 *  @Date 2020/2/8 16:20
 *  @Copyright 2019-2020 
 */

@Target({ ElementType.METHOD, ElementType.FIELD  })
@Retention( RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StringServiceValidator.class,IntServiceValidator.class})
public @interface IServiceValidation {
	String message( ) default "{service validation}";
	Class<?>[] groups( ) default {};
	Class<? extends Payload>[] payload( ) default {};
}
