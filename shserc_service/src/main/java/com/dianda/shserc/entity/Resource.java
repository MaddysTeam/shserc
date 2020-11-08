package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.shserc.util.cache.dictionary.DictionaryCache;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDateTime;

@Data
@EqualsAndHashCode( callSuper = false )
public class Resource {
	
	private long id;
	
	private String title;
	
	private String author;
	
	private String keywords;
	
	private String description;
	
	private String coverPath;
	
	private String resourcePath;
	
	private String fileExtName;
	
	private  String fileName;
	
	private long fileSize;
	
	private boolean isLink;
	
	private String authorCompany;
	
	private String authorAddress;
	
	private String authorEmail;
	
	private String authorPhone;
	
	private long deformityId;
	
	private long resourceTypeId;
	
	private long mediumTypeId;
	
	private long stateId;
	
	private long viewCount;
	
	private long starTotal;
	
	private long auditor;
	
	private LocalDateTime auditorTime;
	
	private long addUser;
	
	private LocalDateTime addTime;
	
	private long updateUser;
	
	private LocalDateTime updateTime;
	
	private boolean isDeleted;
	
	@TableField(exist = false)
	private String deformity;
	
	@TableField(exist = false)
	private String resourceType;
	
	@TableField(exist = false)
	private String mediumType;
	
	@TableField(exist = false)
	private String state;
	
	public boolean isNewOne( ) {
		return this.id <= 0;
	}
	
	
	public static void dictTranslate( Resource resource , DictionaryCache cache ) {
		resource.deformity = cache.translate ( "deformity" , String.valueOf ( resource.getDeformityId ( ) ) );
		resource.state = cache.translate ( "state" , String.valueOf ( resource.getStateId ( ) ) );
	}
	
}
