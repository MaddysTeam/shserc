package com.dianda.shserc.util.upload.wangsu;

import com.chinanetcenter.api.util.Config;

public class WangsuConfig {

	static final String AK = "57b89577283edae1d74e261b7b637f7055e29c08";
	static final String SK = "a5834d0469180f9c30d4b761aac90975cfc83d10";
	static final String UPLOAD_DOMAIN = "tejiao.up28.v1.wcsapi.com";
	static final String MANAGER_DOMAIN = "tejiao.mgr28.v1.wcsapi.com";
	static final String BUCKET = "tejiao-test001";
	static final String DEADLINE = "2527351322000";
	static final String RETURN_BODY_PATTERN = "key=$(key)&fname=$(fname)&fsize=$(fsize)&url=$(url)&hash=$(hash)&mimeType=$(mimeType)";
	//static final String DOMAIN="cdn.sser.shdjg.net";


	public static void init( ) {
		Config.AK = AK;
		Config.SK = SK;
		Config.PUT_URL = UPLOAD_DOMAIN;
		Config.MGR_URL = MANAGER_DOMAIN;
	}

}
