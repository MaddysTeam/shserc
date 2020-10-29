//package com.dianda.shserc.util.cache.dictionary;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.aspectj.lang.annotation.*;
//
//import java.lang.reflect.Method;
//
//@Order( Ordered.HIGHEST_PRECEDENCE)
//@Aspect
//@Component
//public class DictionaryAspect {
//
//	@Pointcut("@annotation(com.dianda.shserc.util.cache.dictionary.Dict)")
//	public void pointCut( ) {
//
//	}
//
//	@Around( "pointCut()" )
//	public Object doAroundService( ProceedingJoinPoint joinPoint ) {
//		String method = joinPoint.getSignature ( ).getName ( );
//		Object[] args = joinPoint.getArgs ( );
//
//		//TODO: will  implement later
//
//		return null;
//	}
//
//
//}
//
//
