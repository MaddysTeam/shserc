package com.dianda.shserc.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user entity
 *
 * @author huachao
 * @since 2020-08-08
 */
@Data
@EqualsAndHashCode( callSuper = false )
public class ResUser extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String password;
	
	private int isDeleted;
	
	private long companyId;
	
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
	
	@TableField( exist = false )
	private String companyName;
	
	@TableField( exist = false )
	private String state;
	
	@TableField( exist = false )
	private String roleName;
	
	@TableField( exist = false )
	private long roleId;
	
	public static long getSerialVersionUID( ) {
		return serialVersionUID;
	}
	
}