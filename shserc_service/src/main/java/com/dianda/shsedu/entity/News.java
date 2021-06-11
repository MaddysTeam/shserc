package com.dianda.shsedu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.common.entity.BaseEntity;
import com.dianda.common.util.cache.dictionary.DictionaryCache;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
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

	public void addViewCount(){
		this.visitCount++;
	}

	public static void dictTranslate(News news, DictionaryCache cache){

	}

}


