package com.dianda.shserc.common;

import java.util.ArrayList;
import java.util.List;

public final class Constant {
	
	public static final class ErrorCode {
		public static String TODO = "002";
		public static String PARAM_NULL_POINT_REFERENCE = "008";
		public static String PARAM_INVALIDATE = "009";
	}
	
	public static final class Error {
		// user error message
		public final static String USER_NAME_IS_REQUIRED = "用户名为必须";
		public final static String USER_IDCard_IS_REQUIRED = "用戶身份证件号为必须";
		public final static String USER_REAL_NAME_IS_REQUIRED = "用戶真实姓名为必须";
		public final static String USER_MOBILE_IS_REQUIRED = "用戶手机号为必须";
		public final static String PASSWORD_IS_REQUIRE = "密码不能为空";
		public final static String EMAIL_FORMAT_INVALID = "邮件格式不正确";
		
		// role and permission error message
		public final static String ROLE_NAME_IS_REQUIRED = "角色名称为必须";
		
		// company error message
		public final static String COMPANY_ID_IS_REQUIRED = "单位ID为必须";
		public final static String COMPANY_NAME_IS_REQUIRED = "单位(地区)名称为必填";
		
		// file and source error message
		public final static String FILE_OBJECT_IS_REQUIRED = "文件数据是必须的";
		public final static String FILE_NAME_IS_REQUIRED = "文件名称为必须";
		public final static String FILE_SIZE_IS_GREATER_THAN_ZERO = "文件大小必须为大于0";
		public final static String FILE_UPLOAD_DST_PATH = "上传文件路径为必须";
		public final static String FILE_TYPE_NOT_ALLOWED = "不允许该文件类型上传";
		
		// bulletin error message
		public final static String BULLETIN_TYPE_IS_NOT_CORRECT = "公告类型不正确";
		public final static String BULLETIN_TITLE_IS_REQUIRE = "公告标题为必填";
		public final static String BULLETIN_CONTENT_IS_REQUIRE = "公告内容为必填";
		public final static String BULLETIN_CONTENT_MAX_LENGTH = "公告内容过多";
		
		// common
		public final static String PARAMS_IS_INVALID = "传入参数有误";
		public final static String EDIT_FAILURE = "编辑失败";
		public final static String OBJECT_IS_REQUIRED = "对象不能为空";
		
	}
	
	public static final class Success {
		public final static String EDIT_SUCCESS = "编辑成功！";
		public final static String UPLOADSUCCESS = "上传成功！";
	}
	
	public static final class ThisApp {
		public final static Integer ROOT_ID = 1;
		public static final String[] ALLOW_FILE_TYPES = { };
		public static final String UPLOAD_FILE_PATH = "";
	}
	
	public static final class State {
		public final static int DELETED = 171;
		public final static int AUDITSUCCESS = 169;
		public final static int AUDITFALURE = 170;
		public final static int ENABLED = 13;
		public final static int DISABLED = 14;
	}
	
	public static final class OrderDirection {
		public final static String ASC = "ASC";
		public final static String DESC = "DESC";
	}
	
	
}
