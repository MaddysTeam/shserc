package com.dianda.common.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( { ElementType.FIELD } )
@Retention( RetentionPolicy.RUNTIME )
@Constraint( validatedBy = { CheckIdCard.class } )
public @interface ICheckIdCard {
	String message( ) default "{service validation}";
	
	Class<?>[] groups( ) default { };
	
	Class<? extends Payload>[] payload( ) default { };
}
