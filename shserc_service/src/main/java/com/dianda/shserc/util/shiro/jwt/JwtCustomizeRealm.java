package com.dianda.shserc.util.shiro.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.exceptions.ExceptionType;
import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.mapper.ResUserMapper;
import com.dianda.shserc.util.SpringUtils;
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

		String userName=dto.getString("userName");
		try {
			ResUser user = userMapper.selectOne (
					new QueryWrapper<ResUser> ( )
							.eq ( "password" , dto.getString ( "password" ) )
							.and(wrapper->
									wrapper.eq ( "user_name" , userName)
											.or()
											.eq("email", userName)
											.or()
											.eq("mobile",userName)
							)
			);
			if ( ObjectUtil.isNull ( user ) ) {
				throw new AuthenticationException ( "用户名或密码错误" );
			} else {
				account = JSON.toJSONString ( user );
				token = JwtOperation.Sign ( account , System.currentTimeMillis ( ) );
			}
			
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
