package com.dianda.shserc.util.shiro.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.exceptions.ExceptionType;
import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.mapper.ResUserMapper;
import com.dianda.shserc.util.SpringUtils;
import com.dianda.shserc.util.basic.EncoderUtil;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.nosql.redis.RedisUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class JwtCustomizeRealm extends AuthorizingRealm {
	
	@Autowired
	RedisUtil redisUtil;
	
	@Resource
	ResUserMapper userMapper;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principalCollection ) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo ( );
		Object principal = principalCollection.getPrimaryPrincipal ( );
		
		//authorizationInfo.addRole ( "admin" );
		
		return authorizationInfo;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authenticationToken ) throws AuthenticationException {
		String token = ( String ) authenticationToken.getPrincipal ( );
		Object credentials = authenticationToken.getCredentials ( );
		String account = JwtOperation.getClaim ( token , JwtConstant.ACCOUNT );
		
		// authenticate logic
		JSONObject dto = JSON.parseObject ( account );
		if ( ObjectUtil.isNull ( dto ) )
			throw new AuthenticationException ( "用户名或密码错误" );
		
		String userName = dto.getString ( "userName" );
		String password = EncoderUtil.SHA ( dto.getString ( "password" ) );
		try {
			ResUser user = null;
			List<ResUser> users = userMapper.selectUsers ( new Page<> ( ) ,
					new QueryWrapper<ResUser> ( )
							.eq ( "password" , password )
							.and ( wrapper ->
									wrapper.eq ( "user_name" , userName )
											.or ( )
											.eq ( "email" , userName )
											.or ( )
											.eq ( "mobile" , userName )
							)
			).getRecords ( );
			
			if ( ObjectUtil.isNull ( users ) || users.size ( ) <= 0 || ObjectUtil.isNull ( users.get ( 0 ) ) ) {
				throw new AuthenticationException ( Constant.Error.LOGIN_WITH_WRONG_INFO );
			}
			
			user = users.get ( 0 );
			account = JSON.toJSONString ( user );
			token = JwtOperation.Sign ( account , System.currentTimeMillis ( ) );
			if ( JwtOperation.verifyToken ( token ) ) {
				// put token into redis
				redisUtil.saveString ( JwtConstant.CACHE_PREFIX + user.getId ( ) , token );
				return new SimpleAuthenticationInfo ( token , credentials , account );
			} else
				throw new GlobalException ( ExceptionType.TOKEN_INVALID , "toke is invalid!" );
			
		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace ( );
		}
		
		throw new AuthenticationException ( "token invalid" );
	}
	
	
	@Override
	public boolean supports( AuthenticationToken token ) {
		return token instanceof JwtToken;
	}
}
