package com.dianda.shserc.util.cache.aspect;

import com.dianda.shserc.util.cache.manager.ICacheManager;
import org.apache.shiro.session.mgt.DelegatingSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;

import javax.annotation.Resource;

@Order( Ordered.HIGHEST_PRECEDENCE)
@Aspect
@Component
public class SetCacheAspect {

	private final String cachePoint="@annotation(com.dianda.shserc.util.cache.aspect.setCache)";

	@Resource( name = "memoryCache" )
	ICacheManager cacheManager;

	@Pointcut(cachePoint)
	public void pointCut( ) {

	}

	//@Around( "pointCut()" )
	public Object doAroundService( ProceedingJoinPoint joinPoint ) {

		//TODO: will  implement later

		return null;
	}


}


