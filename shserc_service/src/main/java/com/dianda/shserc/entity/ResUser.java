package com.dianda.shserc.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.shserc.validators.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.tomcat.jni.Local;

/**
 * user entity
 *
 * @author huachao
 * @since 2020-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NotNull
public class ResUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	
	private String userName;
	
	private String password;
	
	private int isDeleted;
	
	private  Long  companyId;
	
	//TODO: 20210220
	
	private String idCard;
	
	private String realName;
	
	private String photoPath;
	
	private String mobile;
	
	private String email;
	
	private LocalDateTime registerTime;
	
	private LocalDateTime lastLoginTime;
	
	private long stateId;

	private int loginCount;

	@TableField(exist = false)
	private String companyName;

	@TableField(exist = false)
	private  String state;
	
	private long addUser;
	
	private LocalDateTime addTime;
	
	private long updateUser;
	
	private LocalDateTime updateTime;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public boolean isNewOne() {
		return id <= 0;
	}
	
}