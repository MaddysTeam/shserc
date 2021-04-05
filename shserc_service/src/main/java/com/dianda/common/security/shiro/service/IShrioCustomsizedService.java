package com.dianda.common.security.shiro.service;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Huachao
 * @title IShrioCustomsizedService
 * @Description this interface could be use by  multi  implements  and  you can  determine which one will be use or not
 * @Date 2021/4/4 11:00
 * @Copyright 2019-2020
 */


public interface IShrioCustomsizedService {
	
	AuthenticationInfo authenticate( AuthenticationToken authenticationToken );
	
	AuthorizationInfo authorize( PrincipalCollection principalCollection );
	
	Map<String, String> setFilterRoleMap( );
	
}
