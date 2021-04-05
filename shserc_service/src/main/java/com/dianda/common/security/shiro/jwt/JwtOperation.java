package com.dianda.common.security.shiro.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.basic.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * @author huachao
 * @title JwtOperation
 * @Description jwt operation something like verify or get claims
 * @Date 2020/3/8 16:30
 * @Copyright 2019-2020
 */


@Component
public class JwtOperation {
	
	@Autowired
	 JwtSettings jwtSettings;
	
	@Autowired
	private static JwtOperation jwtOperation;
	
	
	public static final String SECRET = "secret";
	
	@PostConstruct
	public void Init() {
		jwtOperation = this;
		jwtOperation.jwtSettings = this.jwtSettings;
	}
	
			
			public static Map<String, Claim> getClaims( String token) {
				Map<String, Claim> claims = null;
				try {
					DecodedJWT jwt = JWT.decode(token);
					claims = jwt.getClaims();
				} catch (JWTDecodeException e) {
				
				} finally {
					return claims;
		}
	}
	
	public static String getClaim( String token, String claim) {
		Map<String, Claim> claims = getClaims(token);
		String result = null;
		if(!ObjectUtil.isNull(claims)) {
			for ( Map.Entry<String, Claim> entry : claims.entrySet()) {
				if (claim == entry.getKey()) {
					result = entry.getValue().asString();
				}
			}
		}
		
		return result;
	}
	
	
	/**
	 * @title token verify
	 * @Description jwt verify by claim and signature,
	 */
	public static boolean verifyToken( String token) throws UnsupportedEncodingException {
		String secret = getClaim(token, JwtConstant.ACCOUNT) +JwtOperation.jwtOperation.jwtSettings;
		Algorithm algorithm = Algorithm.HMAC256(secret);
		JWTVerifier verifier = JWT.require(algorithm).build();
		verifier.verify(token);
		
		return true;
	}
	
	
	public static String Sign( String account, long currentTimeMillions) {
		try {
			String secret = account + JwtOperation.jwtOperation.jwtSettings;
			Date date = new Date ( System.currentTimeMillis() + 1000000);//currentTimeMillions * 1000);
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
							.withClaim("account",account)
							.withClaim("currentTimeMillis", StringUtil.toString(currentTimeMillions))
							.withExpiresAt(date)
							.sign(algorithm);
			
		} catch ( Exception e) {
		   throw e;
		}
	}
	
	/**
	 *  @title refresh token
	 *  @param:	original token
	 *  @return:  refreshed token
	 *  @Description 描述
	 */
	
	public static JwtToken RefreshToken(JwtToken token){
		return token;
	}
	
}
