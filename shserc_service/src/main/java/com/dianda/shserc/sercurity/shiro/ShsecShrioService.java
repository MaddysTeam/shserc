package com.dianda.shserc.sercurity.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dianda.common.exceptions.ExceptionType;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.common.security.shiro.jwt.JwtConstant;
import com.dianda.common.security.shiro.jwt.JwtOperation;
import com.dianda.common.security.shiro.service.IShrioCustomsizedService;
import com.dianda.common.util.basic.EncoderUtil;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.nosql.redis.RedisUtil;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.mapper.ResUserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShsecShrioService implements IShrioCustomsizedService {
	
	@Autowired
	RedisUtil redisUtil;
	
	@Resource
	ResUserMapper userMapper;
	
	@Override
	public AuthenticationInfo authenticate( AuthenticationToken authenticationToken ) {
		String token = ( String ) authenticationToken.getPrincipal ( );
		Object credentials = authenticationToken.getCredentials ( );
		String account = JwtOperation.getClaim ( token , JwtConstant.ACCOUNT );
		
		// authenticate logic
		JSONObject dto = JSON.parseObject ( account );
		if ( ObjectUtil.isNull ( dto ) )
			throw new GlobalException ( ExceptionType.SERVER_ERROR , Constant.Error.LOGIN_WITH_WRONG_INFO );
		
		String userName = dto.getString ( "userName" );
		//String password = dto.getString ( "password" );
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
				throw new GlobalException ( ExceptionType.SERVER_ERROR , Constant.Error.LOGIN_WITH_WRONG_INFO );
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
	public AuthorizationInfo authorize( PrincipalCollection principalCollection ) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo ( );
		Object principal = principalCollection.getPrimaryPrincipal ( );
		
		//authorizationInfo.addRole ( "admin" );
		
		return authorizationInfo;
	}
	
	@Override
	public Map<String, String> setFilterRoleMap( ) {
		
		Map<String, String> filterRuleMap = new LinkedHashMap<> ( );
		// following for swagger
		filterRuleMap.put ( "/" , "anon" );
		filterRuleMap.put ( "/v2/*" , "anon" );
		filterRuleMap.put ( "/csrf" , "anon" );
		filterRuleMap.put ( "/webjars/**" , "anon" );
		filterRuleMap.put ( "/swagger-ui.html" , "anon" );
		filterRuleMap.put ( "/swagger/**" , "anon" );
		filterRuleMap.put ( "/swagger-resources/**" , "anon" );
		filterRuleMap.put ( "/static/**" , "anon" );
		
		// TODO : will  dynamic get data from db
		filterRuleMap.put ( "/account/login" , "anon" );
		filterRuleMap.put ( "/dictionary/*" , "anon" );
		filterRuleMap.put ( "/role/list*" , "anon" );
		filterRuleMap.put ( "/bulletin/list" , "anon" );
		filterRuleMap.put ( "/comment/list" , "anon" );
		filterRuleMap.put ( "/croResource/stars/*" , "anon" );
		filterRuleMap.put ( "/resource/*" , "anon" );
		filterRuleMap.put ( "/resource/stars/*" , "anon" );
		filterRuleMap.put ( "/croResource/*" , "anon" );
		filterRuleMap.put ( "/user/list" , "anon" );
		
		filterRuleMap.put ( "/user/edit/*" , "jwt" );
		filterRuleMap.put ( "/user/role/edit","jwt" );
		filterRuleMap.put ( "/company/*" , "jwt" );
		filterRuleMap.put ( "/resource/edit/*" , "jwt" );
		filterRuleMap.put ( "/resource/favorite/*" , "jwt" );
		filterRuleMap.put ( "/resource/star/*" , "jwt" );
		filterRuleMap.put ( "/resource/download/*" , "jwt" );
		filterRuleMap.put ( "/resource/comment/*" , "jwt" );
		filterRuleMap.put ( "/resource/audit/*" , "jwt" );
		
		filterRuleMap.put ( "/croResource/edit/*" , "jwt" );
		filterRuleMap.put ( "/croResource/favorite/*" , "jwt" );
		filterRuleMap.put ( "/croResource/star/*" , "jwt" );
		filterRuleMap.put ( "/croResource/download/*" , "jwt" );
		filterRuleMap.put ( "/croResource/comment/*" , "jwt" );
		filterRuleMap.put ( "/croResource/audit/*" , "jwt" );
		
		filterRuleMap.put ( "/my/*" , "jwt" );
		filterRuleMap.put ( "/real/*" , "jwt" );
		filterRuleMap.put ( "/file/*" , "jwt" );
		filterRuleMap.put ( "/role/edit" , "jwt" );
		filterRuleMap.put ( "/menu/edit" , "jwt" );
		filterRuleMap.put ( "/account/logout" , "jwt" );
		filterRuleMap.put ( "/**" , "jwt" );
		
		return filterRuleMap;
	}
	
}
