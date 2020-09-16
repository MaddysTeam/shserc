package com.dianda.auth.vo;

import com.dianda.auth.entity.ResCompany;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ResCompanyVo extends BaseVo<ResCompany> implements Serializable {
	
	private long id;
	private String companyName;
	private Map<Long, ResCompanyVo> children;
	
	public ResCompanyVo( long id , String name , Map<Long, ResCompanyVo> children ) {
		this.id = id;
		this.companyName = name;
		this.children = children;
	}
	
}
