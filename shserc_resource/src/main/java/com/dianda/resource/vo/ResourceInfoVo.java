package com.dianda.resource.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.resource.entity.ResourceInfo;

import java.io.Serializable;

public class ResourceInfoVo extends ResourceInfo implements Serializable {

	@TableField("userName" )
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String addUserName) {
		this.userName = addUserName;
	}
}
