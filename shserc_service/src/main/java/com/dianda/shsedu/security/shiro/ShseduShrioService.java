package com.dianda.shsedu.security.shiro;

import com.dianda.common.security.shiro.service.IShrioCustomsizedService;
import com.dianda.common.util.nosql.redis.RedisUtil;
import com.dianda.shsedu.service.IUserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Primary
@Service("ShseduShiroService")
public class ShseduShrioService implements IShrioCustomsizedService {

	@Autowired
	RedisUtil redisUtil;

	@Resource
	IUserService userService;

	@Override
	public AuthenticationInfo authenticate(AuthenticationToken authenticationToken) {
		return null;
	}

	@Override
	public AuthorizationInfo authorize(PrincipalCollection principalCollection) {
		return null;
	}

	@Override
	public Map<String, String> setFilterRoleMap() {
		HashMap<String,String> roleMap=new HashMap<>();
		Map<String, String> filterRuleMap = new HashMap<>( );

		filterRuleMap.put ( "/" , "anon" );
		filterRuleMap.put ( "/v2/*" , "anon" );
		filterRuleMap.put ( "/csrf" , "anon" );
		filterRuleMap.put ( "/webjars/**" , "anon" );

		// following for swagger
		filterRuleMap.put ( "/swagger-ui.html" , "anon" );
		filterRuleMap.put ( "/swagger/**" , "anon" );
		filterRuleMap.put ( "/swagger-resources/**" , "anon" );

		filterRuleMap.put ( "/static/**" , "anon" );

		// TODO : will  dynamic get data from db
		filterRuleMap.put ( "/account/login" , "anon" );
		filterRuleMap.put ( "/dictionary/*" , "anon" );
		filterRuleMap.put ( "/role/list*" , "anon" );

		filterRuleMap.put ( "/file/*" , "jwt" );
		filterRuleMap.put ( "/role/edit" , "jwt" );
		filterRuleMap.put ( "/account/logout" , "jwt" );
		filterRuleMap.put ( "/**" , "jwt" );

		return filterRuleMap;
	}

}
