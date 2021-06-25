package com.dianda.shsedu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dianda.common.common.Constant;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.common.security.shiro.jwt.JwtOperation;
import com.dianda.common.security.shiro.jwt.JwtToken;
import com.dianda.common.security.shiro.util.ShiroUtil;
import com.dianda.common.service.IAccountService;
import com.dianda.common.dto.ChangePasswordDto;
import com.dianda.common.dto.ForgetPasswordDto;
import com.dianda.common.dto.LoginDto;
import com.dianda.common.util.basic.EncoderUtil;
import com.dianda.common.util.logger.system.SystemLog;
import com.dianda.shsedu.entity.ShseduUser;
import com.dianda.shsedu.mapper.ShseduUserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;

@Service("ShseduAccountService")
public class AccountServiceImpl implements IAccountService {

	@Resource
	ShseduUserMapper shseduUserMapper;

	@Override
	@SystemLog
	public boolean login(@Valid LoginDto loginDto) {
		try {
			// login by shiro and jwt
			//String userName = loginDto.getUserName();
			String loginJson = JSON.toJSONString ( loginDto );
			String loginToken = JwtOperation.Sign ( loginJson , System.currentTimeMillis ( ) );
			Subject subject = SecurityUtils.getSubject ( );
			subject.login ( new JwtToken( loginToken ) );

			// if login succes, record last login date and login count
			JSONObject userObject = ShiroUtil.getLoginUser();
			ShseduUser user = new ShseduUser();
			user.setId(userObject.getLong("id"));
			//user.setLastLoginTime( LocalDateTime.now());  // record last login time
			//user.setLoginCount(userObject.getInteger ( "loginCount" ) + 1);  // record login count++

			//shseduUserMapper.updateById(user);

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
	@SystemLog
	public boolean logout() {
		Subject subject = SecurityUtils.getSubject ( );
		if ( subject.isAuthenticated ( ) )
			subject.logout ( );

		return true;
	}

	@Override
	public boolean changePassword( @Valid ChangePasswordDto dto ) {
		if ( ! dto.getConfirmPassword ( ).equals ( dto.getNewPassword ( ) ) )
			throw new GlobalException( Constant.ErrorCode.LOGIC_RESULT_INVALID , Constant.Error.PASSWORD_CONFIRM_FAIL );

		ShseduUser user = new ShseduUser ( );
		user.setPassword ( EncoderUtil.SHA ( dto.getNewPassword ( ) ) );

		LambdaUpdateWrapper<ShseduUser> wrapper = new LambdaUpdateWrapper<> ( );
		wrapper.eq ( ShseduUser :: getUserName , dto.getUserName ( ) );

		return shseduUserMapper.update ( user , wrapper ) >= 0; // zero means old password same as  the new password
	}


	@Override
	public boolean forgetPassword(ForgetPasswordDto dto) {
		return false;
	}

}
