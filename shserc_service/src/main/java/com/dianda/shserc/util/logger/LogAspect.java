package com.dianda.shserc.util.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  @title LogAspect
 *  @Description  the
 *  @author huachao
 *  @Date 2020/10/17 13:45
 *  @Copyright 2019-2020
 */

@Aspect
@Component
public class LogAspect {
	
	private final String dataPoint = "execution(* com.dianda.shserc.util.upload.wangsu.*..*(..))";
	//static Logger logger = ( Logger ) LogManager.getLogger ( LogAspect.class );
	
	@Autowired(required = false)
	IServiceLogger logger;
	
	@Pointcut( dataPoint )
	public void pointCut( ) { }
	
	@Around( "pointCut()" )
	public Object doAroundService( ProceedingJoinPoint joinPoint ) {
		String method = joinPoint.getSignature ( ).getName ( );
		Object[] args = joinPoint.getArgs ( );
		
		//TODO: will  implement later
		
		return null;
	}
	
}
