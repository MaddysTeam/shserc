package com.dianda.shserc.vo;

import com.dianda.shserc.entity.ResUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResUserVo extends BaseVoList<ResUser> implements Serializable {
	 private  long  id;
	 private  String userName;
	 private  long companyId;
	 private  boolean isDeleted;
}
