package com.dianda.shserc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dianda.shserc.dto.ChangePasswordDto;
import com.dianda.shserc.dto.ForgetPasswordDto;
import com.dianda.shserc.dto.LoginDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.mapper.ResUserMapper;
import com.dianda.shserc.service.IAccountService;
import com.dianda.shserc.util.basic.EncoderUtil;
import com.dianda.shserc.util.shiro.jwt.JwtOperation;
import com.dianda.shserc.util.shiro.jwt.JwtToken;
import com.dianda.shserc.util.shiro.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author hauchao
 * @title Account Service Impl
 * @Date 2020/8/9 23:16
 * @Copyright 2019-2020
 */
@Service
public class AccountServiceImpl implements IAccountService {

	@Resource
	ResUserMapper userMapper;

	@Override
	public LoginDto login(@Valid LoginDto loginDto) {
		try {
			// login by shiro and jwt
			String userName = loginDto.getUserName();
			String loginJson = JSON.toJSONString(loginDto);
			String loginToken = JwtOperation.Sign(loginJson, System.currentTimeMillis());
			Subject subject = SecurityUtils.getSubject();
			subject.login(new JwtToken(loginToken));

			// if login succes, get userinfo from shiro
			JSONObject userObject = ShiroUtil.getLoginUser();
			ResUser user = new ResUser();
			user.setId(userObject.getInteger("id"));
			user.setLastLoginTime(LocalDateTime.now());  // record last login time
			user.setLoginCount(userObject.getInteger ( "loginCount" ) + 1);  // record login count++
			userMapper.updateById(user);

			// if login success,we can get token from subject and save it
			String token = subject.getPrincipal().toString();
			loginDto.setIsSuccess(true);
			loginDto.setToken(token);

			return loginDto;
		} catch (AuthenticationException e) {
			loginDto.setIsSuccess(false);
			loginDto.setMessage(e.getMessage());
		}
		return loginDto;
	}


	@Override
	public boolean logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated())
			subject.logout();

		return true;
	}

	@Override
	public ChangePasswordDto changePassword(@Valid ChangePasswordDto dto) {
		ResUser user = new ResUser();
		user.setPassword(EncoderUtil.SHA(dto.getNewPassword()));

		LambdaUpdateWrapper<ResUser> wrapper = new LambdaUpdateWrapper<>();
		wrapper.eq(ResUser::getUserName, dto.getUserName());

		Integer row = userMapper.update(user, wrapper);
		if (row > 0) {
			dto.setIsSuccess(true);
		} else {
			dto.setIsSuccess(false);
		}

		return dto;
	}

	/**
	 * @title forget password
	 * @Description this method to retrieve password by using mobile number
	 * @author hauchao
	 * @Date 2021/2/20 23:31
	 * @Copyright 2019-2020
	 */
	@Override
	public ForgetPasswordDto forgetPassword(ForgetPasswordDto dto) {
		return null;
	}

}
