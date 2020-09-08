package com.dianda.auth.vo;

import com.dianda.auth.entity.ResCompany;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ResCompanyVo extends BaseVo<ResCompany> implements Serializable {
	private Map<Long, ResCompanyVo> hierarchyData;
}
