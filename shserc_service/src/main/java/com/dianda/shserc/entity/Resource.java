package com.dianda.shserc.entity;


import com.dianda.shserc.util.cache.DataDictionaryCache;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

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
	
	private long fileSize;
	
	private boolean isLink;
	
	private String authorCompany;
	
	private String authorAddress;
	
	private String authorEmail;
	
	private String authorPhone;
	
	private long deformityPKID = 1;
	
	private long resourceTypePKID;
	
	private long MediumTypePKID;
	
	private long statePKID;
	
	private long viewCount;
	
	private long starTotal;
	
	private long auditor;
	
	private long auditorTime;
	
	private long addUser;
	
	private LocalDateTime addTime;
	
	private long updateUser;
	
	private LocalDateTime updateTime;
	
	private boolean isDeleted;
	
	
	private String deformity;
	
	public String getDeformity( ) {
		return new DataDictionaryCache ().translate ( "gender" , "" + deformityPKID );
	}
	
	private String resourceType;
	
	private String mediumType;
	
	private String state;
	
	public boolean isNewOne( ) {
		return this.id <= 0;
	}
	
}
