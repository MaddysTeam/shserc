package com.dianda.shserc.util.cache.aspect;

import com.dianda.shserc.util.cache.CacheType;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SetCache {
	CacheType  value();
	String  key() default "";
}
