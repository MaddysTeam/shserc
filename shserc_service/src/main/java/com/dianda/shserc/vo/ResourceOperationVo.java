package com.dianda.shserc.vo;

import com.dianda.common.vo.BaseVoList;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResourceOperationVo implements Serializable {
	
	private long resourceId;
	
	private  long userId;
	
	private  int score;
	
	private  int scoreCount;
	
	private String operationDate; // download , favorite
	
	private String title;
	
	private String author;
	
	private  String fileExtName;
	
}


