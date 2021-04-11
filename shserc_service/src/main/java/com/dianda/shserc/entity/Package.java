package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.common.entity.BaseEntity;
import com.dianda.common.util.cache.dictionary.DictionaryCache;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class Package extends BaseEntity {
	
	private String title;
	
	private String author;
	
	private String keywords;
	
	private String description;
	
	private String coverPath;
	
	private String authorCompany;
	
	private String authorAddress;
	
	private String authorEmail;
	
	private String authorPhone;
	
	private long deformityId;
	
	private long stateId;
	
	private int viewCount;
	
	private long auditor;
	
	private String auditorOpinion;
	
	private LocalDateTime auditorTime;
	
	@TableField(exist = false)
	private String deformity;
	
	@TableField(exist = false)
	private String mediumType;
	
	@TableField(exist = false)
	private String state;
	
	private int downloadCount;
	
	private int favoriteCount;
	
	private int commentCount;
	
	private int starCount;
	
	private int starTotal;
	
	private long subjectId;
	
	private long gradeId;
	
	private long stageId;
	
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
	
	public static void dictTranslate(Package packages, DictionaryCache cache) {
		packages.deformity = cache.translate("deformity", String.valueOf(packages.getDeformityId()));
		packages.state = cache.translate("state", String.valueOf(packages.getStateId()));
		packages.domain = cache.translate("domain", String.valueOf(packages.getDomainId()));
		packages.schoolType = cache.translate("schoolType", String.valueOf(packages.getSchoolTypeId()));
		packages.stage = cache.translate("stage", String.valueOf(packages.getStageId()));
		packages.grade = cache.translate("grade", String.valueOf(packages.getGradeId()));
		packages.learnFrom=cache.translate("learnFrom", String.valueOf(packages.getLearnFromId ()));
		packages.subject=cache.translate("subject", String.valueOf(packages.getSubjectId ()));
	}
	
}



