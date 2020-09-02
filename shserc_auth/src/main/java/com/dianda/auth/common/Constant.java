package com.dianda.auth.common;

public final class Constant {
	
	public static final class Error {
		
		public final static String USER_NAME_IS_REQUIRED = "用户名为必须";
		public final static String PASSWORD_IS_REQUIRE = "密码不能为空";
		public final static String ROLE_NAME_IS_REQUIRED="角色名称为必须";
		
	}
	
	public static final class Status {
		
		public final static Integer DELETED = 1;
	}

	public static class SQL {

		public static final String  SEARCH_USER_BY_COMPANY_ID="select u.id,u.user_name,u.real_name,u.email,c.company_name from res_user u left join res_company c on u.company_id=c.id where c.id=#{companyId}";

	}
}
