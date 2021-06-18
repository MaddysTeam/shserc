package com.dianda.shsedu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.common.common.Constant;
import com.dianda.common.entity.BaseEntity;
import com.dianda.common.util.cache.dictionary.DictionaryCache;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class News extends BaseEntity {
	
	private String  title;

	private String subTitle;

	private String key;
	
	private String content;
	
	private  long  columnId;

	// content or link
	private long typeId;
	
	private  int  visitCount;
	
	private  int statusId;

	private long districtId;

	private  LocalDateTime publishTime;
	
	private LocalDateTime  auditTime;

	private String coverPath;

	@TableField(exist = false)
	private String type;

	@TableField(exist = false)
	private  String status;

	@TableField(exist = false)
	private  String district;

	public void addViewCount(){
		this.visitCount++;
	}

	public static void dictTranslate(News news, DictionaryCache cache){
		news.type=cache.translate("newsType", String.valueOf(news.typeId), Constant.Dictonary.All);
		news.status=cache.translate("Status", String.valueOf(news.statusId), Constant.Dictonary.All);
		news.district=cache.translate("district",String.valueOf(news.districtId),Constant.Dictonary.All);
	}

}


