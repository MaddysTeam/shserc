package com.dianda.shserc.util.logger;

import com.dianda.shserc.util.basic.ObjectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *  @title LogAspect
 *  @Description  aop for log
 *  @author huachao
 *  @Date 2020/10/17 13:45
 *  @Copyright 2019-2020
 */

@Aspect
@Component
public class LogAspect {

	private final String dataPoint = "execution(* com.dianda.shserc.util.*..*(..))";

	@Resource
	private IServiceLogger logger;

	@Pointcut( dataPoint )
	public void pointCut( ) { }
	
	@Around( "pointCut()" )
	public Object doAroundService( ProceedingJoinPoint joinPoint ) throws Throwable {
		String method = joinPoint.getSignature ( ).getName ( );
		Object[] args = joinPoint.getArgs ( );
		String className=joinPoint.getClass().getName();

		//TODO: service will implement later

		// system log record
		if(!ObjectUtil.isNull(logger)){
			logger.system(new SystemLoggerMessage(className,method,args));
		}

		Object result= joinPoint.proceed();
		return result;
	}
	
}
