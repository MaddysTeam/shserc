package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResUserRole {
	
	private  long roleId;

	private  long userId;

	private LocalDateTime addTime;

}

