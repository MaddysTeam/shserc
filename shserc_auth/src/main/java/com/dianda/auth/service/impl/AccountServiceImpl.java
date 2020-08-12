package com.dianda.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dianda.auth.dto.ChangePasswordDto;
import com.dianda.auth.dto.LoginDto;
import com.dianda.auth.entity.ResUser;
import com.dianda.auth.mapper.ResUserMapper;
import com.dianda.auth.service.IAccountService;
import com.dianda.auth.util.basic.ObjectUtil;
import com.dianda.auth.util.shiro.jwt.JwtOperation;
import com.dianda.auth.util.shiro.jwt.JwtToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hauchao
 * @title AccountServiceImpl
 * @Description account service with following function: login , logout and change password
 * @Date 2020/8/9 23:16
 * @Copyright 2019-2020
 */
@Service
public class AccountServiceImpl implements IAccountService {
	
	@Resource
	ResUserMapper userMapper;
	
	@Override
	public LoginDto login( LoginDto login ) {
		try {
			String loginJson = JSON.toJSONString ( login );
			String loginToken = JwtOperation.Sign ( loginJson , System.currentTimeMillis ( ) );
			Subject subject = SecurityUtils.getSubject ( );
			subject.login ( new JwtToken ( loginToken ) );
			
			// if login success,we can get token from subject
			String token=subject.getPrincipal ().toString ();
			login.setLoginSuccess ( true );
			login.setToken ( token);
			
			return login;
		} catch ( AuthenticationException e ) {
			login.setLoginSuccess ( false );
			login.setMessage ( e.getMessage () );
		}
		return login;
	}
	
	
	@Override
	public boolean logout( ) {
		Subject subject = SecurityUtils.getSubject ( );
		if ( subject.isAuthenticated ( ) )
			subject.logout ( );
		
		return true;
	}

	@Override
	public ChangePasswordDto changePassword(ChangePasswordDto dto) {
		return null;
	}

}
