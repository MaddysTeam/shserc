package com.dianda.resource.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huachao
 * @since 2020-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableField("userName" )
	private String userName;

	@TableField("id" )
	private String id;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}