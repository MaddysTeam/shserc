package com.dianda.shsedu.security.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianda.common.common.Constant;
import com.dianda.common.exceptions.ExceptionType;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.common.security.shiro.jwt.JwtConstant;
import com.dianda.common.security.shiro.jwt.JwtOperation;
import com.dianda.common.security.shiro.service.IShrioCustomsizedService;
import com.dianda.common.util.basic.EncoderUtil;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.nosql.redis.RedisUtil;
import com.dianda.shsedu.service.IUserService;
import com.dianda.shsedu.vo.UserVo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
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
		String token = ( String ) authenticationToken.getPrincipal ( );
		Object credentials = authenticationToken.getCredentials ( );
		String account = JwtOperation.getClaim ( token , JwtConstant.ACCOUNT );

		// authenticate logic
		JSONObject dto = JSON.parseObject ( account );
		if ( ObjectUtil.isNull ( dto ) )
			throw new GlobalException( ExceptionType.SERVER_ERROR , Constant.Error.LOGIN_WITH_WRONG_INFO );

		String userName = dto.getString ( "userName" );
		String password = EncoderUtil.SHA ( dto.getString ( "password" ) );
		try {
			UserVo userVo= userService.findByNameAndPassword(userName,password);
			if ( ObjectUtil.isNull ( userVo ) ) {
				throw new GlobalException ( ExceptionType.SERVER_ERROR , Constant.Error.LOGIN_WITH_WRONG_INFO );
			}

			account = JSON.toJSONString ( userVo );
			token = JwtOperation.Sign ( account , System.currentTimeMillis ( ) );
			if ( JwtOperation.verifyToken ( token ) ) {
				// put token into redis
				redisUtil.saveString ( JwtConstant.CACHE_PREFIX + userVo.getId ( ) , token );
				return new SimpleAuthenticationInfo( token , credentials , account );
			} else
				throw new GlobalException ( ExceptionType.TOKEN_INVALID , "toke is invalid!" );

		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace ( );
		}

		throw new AuthenticationException( "token invalid" );
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
		filterRuleMap.put ( "/shseduAccount/login" , "anon" );
		filterRuleMap.put ( "/dictionary/*" , "anon" );
		filterRuleMap.put ( "/shseduRole/list*" , "anon" );

		filterRuleMap.put ( "/file/*" , "jwt" );
		filterRuleMap.put ( "/shseduRole/edit" , "jwt" );
		filterRuleMap.put ( "/shseduAccount/logout" , "jwt" );
		filterRuleMap.put ( "/**" , "jwt" );

		return filterRuleMap;
	}

}
