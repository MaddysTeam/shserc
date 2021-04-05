package com.dianda.common.security.shiro.session;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  @title CustomizeSessionListener
 *  @Description customize  shiro session listener
 *  @author hauchao
 *  @Date 2020/2/23 16:24
 *  @Copyright 2019-2020
 */

public class CustomizeSessionListener implements SessionListener {
	
	/**
	 *  @Description 描述
	 */
	
	AtomicInteger counter=new AtomicInteger (0);
	
	@Override
	public void onStart(Session session) {
	   counter.incrementAndGet();
	}
	
	@Override
	public void onStop(Session session) {
	
	}
	
	@Override
	public void onExpiration(Session session) {
	
	}
	
}
