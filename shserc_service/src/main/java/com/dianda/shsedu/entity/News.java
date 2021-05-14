package com.dianda.shsedu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.common.entity.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class News extends BaseEntity {
	
	private String  title;
	
	private String content;
	
	private  long  columnId;
	
	private long typeId;
	
	private  int  visitCount;
	
	private  int statusId;
	
	private LocalDateTime  auditTime;

	@TableField(exist = false)
	private String type;

	@TableField(exist = false)
	private  String status;

	public void dictTranslate(News news){

	}

}


