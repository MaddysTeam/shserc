package com.dianda.common.util.cache.aspect;

import com.dianda.common.util.cache.manager.ICacheManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Order( Ordered.HIGHEST_PRECEDENCE)
@Aspect
@Component
public class CleanCacheAspect {

	private final String cachePoint="@annotation(com.dianda.common.util.cache.aspect.cleanCache)";

	@Resource( name = "memoryCache" )
	ICacheManager cacheManager;

	@Pointcut(cachePoint)
	public void pointCut( ) {

	}

	//@Around( "pointCut()" )
	public Object doAroundService( ProceedingJoinPoint joinPoint ) {

		//TODO: will  implement later
		// get  cache key from  clean cache annotation  and  remove this cache by this key

		return null;
	}


}


