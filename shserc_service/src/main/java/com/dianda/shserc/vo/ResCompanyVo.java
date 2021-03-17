package com.dianda.shserc.vo;

import com.dianda.shserc.entity.ResCompany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class ResCompanyVo implements Serializable {

	private long id;
	private long value;
	private String label;
	private ArrayList<ResCompanyVo> children;

	public ResCompanyVo() {
		this.children = new ArrayList<> (  );
	}

	public ResCompanyVo(long id, String name, ArrayList<ResCompanyVo> children) {
		this.id = id;
		this.label = name;
		this.children = children;
	}

}
