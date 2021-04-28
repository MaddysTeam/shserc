package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.common.entity.BaseEntity;
import com.dianda.common.util.cache.dictionary.DictionaryCache;
import com.dianda.shserc.common.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class Resource extends BaseEntity {
	
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

	private String auditorOpinion;

	private LocalDateTime auditorTime;

	@TableField(exist = false)
	private String deformity;

	@TableField(exist = false)
	private String resourceType;

	@TableField(exist = false)
	private String mediumType;

	@TableField(exist = false)
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
	
	private long learnFromId;

	@TableField(exist = false)
	private String subject;

	@TableField(exist = false)
	private String grade;

	@TableField(exist = false)
	private String stage;

	@TableField(exist = false)
	private String importSource;

	@TableField(exist = false)
	private String domain;

	@TableField(exist = false)
	private String schoolType;
	
	@TableField(exist = false)
	private  String learnFrom;

	public void addViewCount() {
		this.setViewCount(this.getViewCount() + 1);
	}

	public void addDownloadCount() {
		this.setDownloadCount(this.getDownloadCount() + 1);
	}

	public void addFavoriteCount() {
		this.setFavoriteCount(this.getFavoriteCount() + 1);
	}

	public void deleteFavoriteCount() {
		this.setFavoriteCount(this.getFavoriteCount() - 1);
	}

	public void addCommentCount() {
		this.setCommentCount(this.getCommentCount() + 1);
	}

	public void addStarCount(int score) {
		this.setStarCount(this.getStarCount() + 1);
		this.setStarTotal(this.getStarTotal() + score);
	}

	public static void dictTranslate(Resource resource, DictionaryCache cache) {
		resource.deformity = cache.translate("deformity", String.valueOf(resource.getDeformityId()), Constant.Dictonary.All );
		resource.state = cache.translate("state", String.valueOf(resource.getStateId()), Constant.Dictonary.All );
		resource.domain = cache.translate("domain", String.valueOf(resource.getDomainId()), Constant.Dictonary.All );
		resource.importSource = cache.translate("importSource", String.valueOf(resource.getImportSourceId()), Constant.Dictonary.All );
		resource.schoolType = cache.translate("schoolType", String.valueOf(resource.getSchoolTypeId()), Constant.Dictonary.All );
		resource.stage = cache.translate("stage", String.valueOf(resource.getStageId()), Constant.Dictonary.All );
		resource.grade = cache.translate("grade", String.valueOf(resource.getGradeId()), Constant.Dictonary.All );
		resource.learnFrom=cache.translate("learnFrom", String.valueOf(resource.getLearnFromId ()), Constant.Dictonary.All );
		resource.resourceType=cache.translate("resourceType", String.valueOf(resource.getResourceTypeId ()), Constant.Dictonary.All );
		resource.subject=cache.translate("subject", String.valueOf(resource.getSubjectId ()), Constant.Dictonary.All );
	}
}

