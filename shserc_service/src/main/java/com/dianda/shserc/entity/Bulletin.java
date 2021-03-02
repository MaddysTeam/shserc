package com.dianda.shserc.entity;

import com.dianda.shserc.util.cache.dictionary.DictionaryCache;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Bulletin {

	private long id;

	private long typeId;

	private String title;

	private String content;

	private boolean isTop;

	private long addUser;

	private LocalDateTime addTime;

	private long updateUser;

	private LocalDateTime updateTime;

	private String type;

	private boolean isDeleted;

	public boolean isNewOne() {
		return this.id <= 0;
	}

	public static void dictTranslate( Bulletin bulletin , DictionaryCache cache ) {
		//bulletin.type = cache.translate ( "bulltinType" , String.valueOf ( bulletin.getTypeId ( ) ) );
	}

}
