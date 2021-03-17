package com.dianda.shserc.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResRealVo implements Serializable {

	private  long id;

	private String realName;

	private String cardNo;

	private String idCard;

	private String cardPassword;

	private  String companyName;

	private long companyId;

}
