package com.dianda.shserc.common;

import java.util.ArrayList;
import java.util.List;

public final class Constant {
	
	public static final class Error {
		
		public final static String USER_NAME_IS_REQUIRED = "用户名为必须";
		public final static String PASSWORD_IS_REQUIRE = "密码不能为空";
		public final static String ROLE_NAME_IS_REQUIRED="角色名称为必须";
		public final static String COMPANY_NAME_IS_REQUIRED="单位(地区)名称为必填";
		public final static String PARAMS_IS_INVALID="传入参数有误";
		
		public final static String FILE_OBJECT_IS_REQUIRED="文件数据是必须的";
		public final static String FILE_NAME_IS_REQUIRED="文件名称为必须";
		public final static String FILE_SIZE_IS_GREATER_THAN_ZERO="文件大小必须为大于0";
		public final static String FILE_UPLOAD_DST_PATH="上传文件路径为必须";
		public final static String FILE_TYPE_NOT_ALLOWED="不允许该文件类型上传";
	}

	public static final class Success{
        public  final static  String EDIT_SUCCESS="编辑成功！";
		public  final static  String UPLOADSUCCESS="上传成功！";
	}

	public static final class ThisApp{
		public final static Integer ROOT_ID=1;
		public static final String[] ALLOW_FILE_TYPES= {};
		public static final String UPLOAD_FILE_PATH="";
	}
	
	public static final class Status {
		public final static boolean DELETED = true	;
	}
	
}
