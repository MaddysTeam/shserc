package com.dianda.shserc.vo;

import com.dianda.shserc.entity.ResUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ResUserVo implements Serializable {

	private long id;
	private String userName;
	private String photoPath;
	private long companyId;
	private String idCard;
	private String mobile;
	private String email;
	private String companyName;
	private String state;
	private long roleId;
	private String roleName;
	private String realName;
	private LocalDateTime registerTime;
	private LocalDateTime lastLoginTime;
	private int loginCount;

	private  String token;

}

