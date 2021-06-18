package com.dianda.shsedu.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
	private long id;
	private String userName;
	private long districtId;
	private String realName;
	private long roleId;
	private String mobile;
	private String email;

	private String token;
}
