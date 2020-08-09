package com.dianda.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dianda.auth.dto.LoginDto;
import com.dianda.auth.entity.ResUser;
import com.dianda.auth.mapper.ResUserMapper;
import com.dianda.auth.service.IAccountService;
import com.dianda.auth.util.basic.ObjectUtil;
import com.dianda.auth.util.shiro.jwt.JwtOperation;
import com.dianda.auth.util.shiro.jwt.JwtToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 *  @title AccountServiceImpl
 *  @Description 描述
 *  @author zhangqiang
 *  @Date 2020/8/9 23:16
 *  @Copyright 2019-2020 
 */

public class AccountServiceImpl implements IAccountService {
	
	@Resource
	ResUserMapper userMapper;
	
	@Override
	public LoginDto Login( LoginDto login ) {
		
		ResUser user = userMapper.selectOne (
				new QueryWrapper<ResUser> (  )
						.eq ( "userName",login.getUserName ())
						.eq (  "password",login.getPassword ()));
		if( ObjectUtil.isNull ( user)) {
			login.setLoginSuccess ( false );
			return login;
		}
		
		String userJson=JSON.toJSONString ( user );
		String token = JwtOperation.Sign ( userJson, System.currentTimeMillis ( ) );
		
		try {
			Subject subject = SecurityUtils.getSubject ( );
			subject.login ( new JwtToken ( token ) );
			login.setLoginSuccess ( true );
			login.setToken ( token );
			
			return login;
		}
		catch (  AuthenticationException e ){
		  login.setLoginSuccess ( false );
		}
		return login;
	}
}
