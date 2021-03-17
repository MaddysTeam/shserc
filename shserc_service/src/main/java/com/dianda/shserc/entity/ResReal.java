package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.shserc.validators.ICheckIdCard;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * real card info entity
 *
 * @author huachao
 * @since 2020-10-05
 */
@Data
public class ResReal extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String realName;

	private String cardNo;

	@ICheckIdCard()
	private String idCard;

	private String cardPassword;

	private long stateId;

	private long companyId;
	
	@TableField( exist = false )
	private String companyName;
	
	@TableField( exist = false )
	private LocalDateTime birthday;
	
	@TableField( exist = false )
	private String State;

//	private long addUser;
//
//	private LocalDateTime addTime;
//
//	private long updateUser;
//
//	private LocalDateTime updateTime;
//
//	public boolean isNewOne() {
//		return this.id <= 0;
//	}

}
