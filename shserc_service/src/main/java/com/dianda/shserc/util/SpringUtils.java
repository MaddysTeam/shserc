package com.dianda.shserc.util;

import lombok.extern.slf4j.XSlf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {
	private static org.springframework.context.ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(org.springframework.context.ApplicationContext  applicationContext)
			throws BeansException {
		if (SpringUtils.applicationContext == null) {
			SpringUtils.applicationContext = applicationContext;
		}
		
	}
	
	public static org.springframework.context.ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	//根据name
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
	
	//根据类型
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}
	
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
	

}
