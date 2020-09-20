package com.dianda.shserc.vo;

import com.dianda.shserc.entity.ResCompany;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ResCompanyVo extends BaseVo<ResCompany> implements Serializable {
	
	private long id;
	private String companyName;
	private Map<Long, ResCompanyVo> children;

	public ResCompanyVo(){}
	
	public ResCompanyVo( long id , String name , Map<Long, ResCompanyVo> children ) {
		this.id = id;
		this.companyName = name;
		this.children = children;
	}
	
}
