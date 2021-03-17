package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.shserc.util.cache.dictionary.DictionaryCache;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Bulletin extends BaseEntity{
	
	private long typeId;

	private String title;

	private String content;

	private boolean top;

	private String filePath;

	private String fileName;

	private String fileExtName;

	private long fileSize;

	@TableField(exist=false)
	private String type;
	
	public static void dictTranslate( Bulletin bulletin , DictionaryCache cache ) {
		//bulletin.type = cache.translate ( "bulltinType" , String.valueOf ( bulletin.getTypeId ( ) ) );
	}

}
