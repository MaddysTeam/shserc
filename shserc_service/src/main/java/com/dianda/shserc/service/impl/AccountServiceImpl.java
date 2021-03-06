package com.dianda.shserc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dianda.common.security.shiro.util.ShiroUtil;
import com.dianda.common.common.Constant;
import com.dianda.common.dto.ChangePasswordDto;
import com.dianda.common.dto.ForgetPasswordDto;
import com.dianda.common.dto.LoginDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.shserc.mapper.ResUserMapper;
import com.dianda.common.service.IAccountService;
import com.dianda.common.util.basic.EncoderUtil;
import com.dianda.common.util.logger.system.SystemLog;
import com.dianda.common.security.shiro.jwt.JwtOperation;
import com.dianda.common.security.shiro.jwt.JwtToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author hauchao
 * @title Account Service Impl
 * @Date 2020/8/9 23:16
 * @Copyright 2019-2020
 */

@Service("ShsercAccountService")
@Validated
public class AccountServiceImpl implements IAccountService {
	
	@Resource
	ResUserMapper userMapper;
	
	@Override
	@SystemLog()
	public boolean login( @Valid LoginDto loginDto ) {
		try {
			// login by shiro and jwt
			//String userName = loginDto.getUserName();
			String loginJson = JSON.toJSONString ( loginDto );
			String loginToken = JwtOperation.Sign ( loginJson , System.currentTimeMillis ( ) );
			Subject subject = SecurityUtils.getSubject ( );
			subject.login ( new JwtToken ( loginToken ) );

			// if login succes, record last login date and login count
			JSONObject userObject = ShiroUtil.getLoginUser();
			ResUser user = new ResUser();
			user.setId(userObject.getLong("id"));
			user.setLastLoginTime( LocalDateTime.now());  // record last login time
			user.setLoginCount(userObject.getInteger ( "loginCount" ) + 1);  // record login count++
			userMapper.updateById(user);
			
			// if login success,we can get token from subject and save it
			String token = subject.getPrincipal ( ).toString ( );
			loginDto.setIsSuccess ( true );
			loginDto.setToken ( token );
			
			return true;
		} catch ( AuthenticationException e ) {
			loginDto.setIsSuccess ( false );
			loginDto.setMessage ( e.getMessage ( ) );
			return false;
		}
	}
	
	
	@Override
	@SystemLog()
	public boolean logout( ) {
		Subject subject = SecurityUtils.getSubject ( );
		if ( subject.isAuthenticated ( ) )
			subject.logout ( );
		
		return true;
	}
	
	@Override
	public boolean changePassword( @Valid ChangePasswordDto dto ) {
		if ( ! dto.getConfirmPassword ( ).equals ( dto.getNewPassword ( ) ) )
			throw new GlobalException ( Constant.ErrorCode.LOGIC_RESULT_INVALID , Constant.Error.PASSWORD_CONFIRM_FAIL );
		
		ResUser user = new ResUser ( );
		user.setPassword ( EncoderUtil.SHA ( dto.getNewPassword ( ) ) );
		
		LambdaUpdateWrapper<ResUser> wrapper = new LambdaUpdateWrapper<> ( );
		wrapper.eq ( ResUser :: getUserName , dto.getUserName ( ) );
		
		return userMapper.update ( user , wrapper ) >= 0; // zero means old password same as  the new password
	}
	
	/**
	 * @title forget password
	 * @Description this method to retrieve password by using mobile number
	 * @author hauchao
	 * @Date 2021/2/20 23:31
	 * @Copyright 2019-2020
	 */
	@Override
	public boolean forgetPassword( ForgetPasswordDto dto ) {
		return false;
	}
	
}
