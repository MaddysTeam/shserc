package com.dianda.shsedu.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class NewsVo implements Serializable {
	long id;

	private String  title;

	private String subTitle;

	private String keyword;

	private String content;

	private  long  columnId;

	private long typeId;

	private  int  visitCount;

	private String type;

	private  String status;

	private  long  districtId;

	private  String district;

	private  String coverPath;

}
