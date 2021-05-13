package com.dianda.shsedu.entity;

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
	
	private  int status;
	
	private LocalDateTime  auditTime;
}


