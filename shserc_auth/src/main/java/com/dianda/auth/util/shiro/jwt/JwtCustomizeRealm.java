package com.dianda.auth.util.shiro.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dianda.auth.entity.ResUser;
import com.dianda.auth.mapper.ResUserMapper;
import com.dianda.auth.util.basic.ObjectUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public class JwtCustomizeRealm extends AuthorizingRealm {
	
	@Resource
	ResUserMapper userMapper;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principalCollection ) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo ( );
		Object principal = principalCollection.getPrimaryPrincipal ( );
		
		authorizationInfo.addRole ( "admin" );
		
		return authorizationInfo;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authenticationToken ) throws AuthenticationException {
		String token = ( String ) authenticationToken.getPrincipal ( );
		Object credentials = authenticationToken.getCredentials ( );
		String account = JwtOperation.getClaim ( token , JwtConstant.ACCOUNT );
		
		// authenticate logic
		JSONObject dto = JSON.parseObject ( account );
		ResUser user = userMapper.selectOne (
				new QueryWrapper<ResUser> ( )
						.eq ( "user_name" , dto.getString ("userName" ) )
						.eq ( "password" ,  dto.getString ("password" )) );
		if ( ObjectUtil.isNull ( user ) ) {
			throw new AuthenticationException ( "用户名或密码错误" );
		}
		else{
			account= JSON.toJSONString ( user );
			token=JwtOperation.Sign ( account ,System.currentTimeMillis ( ));
		}
		
		try {
			if ( JwtOperation.verifyToken ( token ) )
				return new SimpleAuthenticationInfo ( token , credentials , account );
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
