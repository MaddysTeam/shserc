package com.dianda.shserc.entity;

import com.dianda.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class Package extends BaseEntity {
	
	private  String  title;

	private  String  description;

	private String coverPath;

	private int downloadCount;
	
	private int favoriteCount;

	private List<Resource> resources;
	
}



