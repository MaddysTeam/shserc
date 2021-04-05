package com.dianda.shserc.entity;

import com.dianda.common.entity.BaseEntity;
import lombok.Data;

@Data
public class Package extends BaseEntity {
	
	private  String  title;
	private  String  description;
	private String coverPath;
	private int downloadCount;
	
	private int favoriteCount;
	
}



