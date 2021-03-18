package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * user role entity
 *
 * @author huachao
 * @since 2020-08-08
 */
@Data
public class ResUserRole {
	
	private  long roleId;

	private  long userId;

	private LocalDateTime addTime;

}

