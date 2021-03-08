package com.dianda.shserc.vo;

import com.dianda.shserc.entity.ResUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class ResUserVo implements Serializable {
	 private  long  id;
	 private  String userName;
	 private  long companyId;
	 private  boolean isDeleted;
}

