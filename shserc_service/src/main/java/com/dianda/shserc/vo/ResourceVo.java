package com.dianda.shserc.vo;

import com.dianda.shserc.entity.Resource;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ResourceVo extends  BaseVo<ResourceVo> implements Serializable {
	
	private  long id;
	
	private  String title;
	
	private  String author;
	
	private  String resourceType;
	
	private  String deformity;
	
	private  String state;
	
	private  String MediumType;
	
	private  String fileExtName;
	
	private  long  fileSize;
	
	private String authorEmail;
	
	private  long viewCount;
	
	private LocalDateTime addTime;
	
}
