package com.dianda.shserc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dianda.shserc.dto.ChangePasswordDto;
import com.dianda.shserc.dto.LoginDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.mapper.ResUserMapper;
import com.dianda.shserc.service.IAccountService;
import com.dianda.shserc.util.shiro.jwt.JwtOperation;
import com.dianda.shserc.util.shiro.jwt.JwtToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;

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
	public LoginDto login( @Valid LoginDto login ) {
		try {
			String loginJson = JSON.toJSONString ( login );
			String loginToken = JwtOperation.Sign ( loginJson , System.currentTimeMillis ( ) );
			Subject subject = SecurityUtils.getSubject ( );
			subject.login ( new JwtToken ( loginToken ) );
			
			// if login success,we can get token from subject
			String token = subject.getPrincipal ( ).toString ( );
			login.setIsSuccess ( true );
			login.setToken ( token );
			
			return login;
		} catch ( AuthenticationException e ) {
			login.setIsSuccess ( false );
			login.setMessage ( e.getMessage ( ) );
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
	public ChangePasswordDto changePassword( @Valid ChangePasswordDto dto ) {
		ResUser user = new ResUser ( );
		user.setPassword ( dto.getNewPassword ( ) );
		
		LambdaUpdateWrapper<ResUser> wrapper = new LambdaUpdateWrapper<> ( );
		wrapper.eq ( ResUser :: getUserName , dto.getUserName ( ) );
		
		Integer row = userMapper.update ( user , wrapper );
		if ( row > 0 ) {
			dto.setIsSuccess ( true );
		} else {
			dto.setIsSuccess ( false );
		}
		
		return dto;
	}
	
}
