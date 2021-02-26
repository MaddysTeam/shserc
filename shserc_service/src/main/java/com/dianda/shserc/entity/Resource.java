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
	
	private String fileName;
	
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
	
	private int viewCount;
	
	private long auditor;
	
	private LocalDateTime auditorTime;
	
	private long addUser;
	
	private LocalDateTime addTime;
	
	private long updateUser;
	
	private LocalDateTime updateTime;
	
	private boolean isDeleted;
	
	@TableField( exist = false )
	private String deformity;
	
	@TableField( exist = false )
	private String resourceType;
	
	@TableField( exist = false )
	private String mediumType;
	
	@TableField( exist = false )
	private String state;

	//20210226
	private int downloadCount;

	private int favoriteCount;

	private int commentCount;

	private int starCount;

	private int starTotal;

	private long subjectId;

	private long gradeId;

	private long stageId;

	private long importSourceId;

	private long schoolTypeId;

	private long domainId;

//	@TableField( exist = false )
//	private String grade;
//
//	@TableField( exist = false )
//	private String importType;
//
//	@TableField( exist = false )
//	private String subject;
//
//	@TableField( exist = false )
//	private String domain;

	public boolean isNewOne( ) {
		return this.id <= 0;
	}
	
	public void addViewCount( ) {
		this.setViewCount ( this.getViewCount ( ) + 1 );
	}
	
	public static void dictTranslate( Resource resource , DictionaryCache cache ) {
		resource.deformity = cache.translate ( "deformity" , String.valueOf ( resource.getDeformityId ( ) ) );
		resource.state = cache.translate ( "state" , String.valueOf ( resource.getStateId ( ) ) );
	}
	
}
