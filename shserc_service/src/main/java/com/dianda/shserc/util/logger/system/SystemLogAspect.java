package com.dianda.shserc.util.logger.system;

import com.alibaba.fastjson.JSONObject;
import com.dianda.shserc.util.basic.HttpUtil;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.logger.IServiceLogger;
import com.dianda.shserc.util.shiro.util.ShiroUtil;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author huachao
 * @title LogAspect
 * @Description aop for system log
 * @Date 2020/10/17 13:45
 * @Copyright 2019-2020
 */

@Aspect
@Configuration
public class SystemLogAspect {
	
	private final String systemLogDataPoint = "@annotation(com.dianda.shserc.util.logger.system.SystemLog)";
	private final String exceptionDataPoint = "execution(* com.dianda.shserc.controller.*..*(..))";

	@Resource
	private IServiceLogger logger;

	@Pointcut(systemLogDataPoint)
	public void pointCut() {
	}

	@Around("pointCut()")
	public Object doAroundService(ProceedingJoinPoint joinPoint) throws Throwable {
		// get method ,args and class by using jointPoint
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		Object[] args = joinPoint.getArgs();
		Class<?> classObject = joinPoint.getClass();

		// get http servlet request
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

		// get request ip
		String sourceIp = HttpUtil.getRequestIP(request);

		// get login user info from shiro
		JSONObject user = ShiroUtil.getLoginUser();
		String userName = ObjectUtil.isNull(user) ? "unknow" : user.getString("userId");

		// system log record
		if (!ObjectUtil.isNull(logger)) {
			logger.system(new SystemLoggerMessage(
					userName + ":" + sourceIp,
					classObject.getName(),
					method.getName(),
					args));
		}

		return joinPoint.proceed();
	}

//	@Pointcut(exceptionDataPoint)
//	public void exceptionPointCut() {
//	}

//	@AfterThrowing(pointcut = "exceptionPointCut()", throwing = "e")
//	public void occurExceptionLog(Joinpoint joinpoint, Throwable e) {

//	}


}
