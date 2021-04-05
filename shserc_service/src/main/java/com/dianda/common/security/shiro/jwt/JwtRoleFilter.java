package com.dianda.common.security.shiro.jwt;

import com.dianda.common.util.basic.ObjectUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class JwtRoleFilter extends AuthorizationFilter {
	
	@Override
	protected boolean isAccessAllowed( ServletRequest req , ServletResponse resp , Object mappedValue ) throws Exception {
		Subject subject = getSubject ( req , resp );
		String[] roles = ( String[] ) mappedValue;
		if(ObjectUtil.isNull ( subject ) || ObjectUtil.isNull ( mappedValue ) )
			return  false;
		try {
			if ( ! ObjectUtil.isNull ( roles ) ) {
				for ( String role : roles ) {
					if ( subject.hasRole ( role ) ) {
						return true;
					}
				}
				return false;
			} else {
				return true;
			}
		}catch ( Exception e ){
			return false;
		}
	}
}
