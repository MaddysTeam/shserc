package com.dianda.common.util.cache.aspect;

import com.dianda.common.util.cache.CacheType;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CleanCache {
	CacheType  value();
	String  key() default "";
}
