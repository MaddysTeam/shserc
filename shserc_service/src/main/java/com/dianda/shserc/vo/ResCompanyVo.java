package com.dianda.shserc.vo;

import com.dianda.shserc.entity.ResCompany;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class ResCompanyVo extends BaseVo<ResCompany> implements Serializable {

	private long id;
	private String label;
	private Map<Long, ResCompanyVo> children;

	public ResCompanyVo() {
		this.children = new HashMap<>();
	}

	public ResCompanyVo(long id, String name, Map<Long, ResCompanyVo> children) {
		this.id = id;
		this.label = name;
		this.children = children;
	}

}
