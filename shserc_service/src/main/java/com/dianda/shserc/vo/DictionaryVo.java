package com.dianda.shserc.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class DictionaryVo extends BaseVoList<DictionaryVo> implements Serializable {

	private long id;
	private long parentId;
	private long relevantId;
	private String code;
	private String name;
	private long sort;
	private String value;

}
