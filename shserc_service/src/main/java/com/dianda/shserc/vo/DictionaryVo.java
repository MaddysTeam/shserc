package com.dianda.shserc.vo;

import com.dianda.shserc.entity.Dictionary;
import com.dianda.shserc.entity.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class DictionaryVo extends  BaseVo<DictionaryVo> implements Serializable {
	private long id;
	private long parentId;
	private String code;
	private String name;
	private long sort;
	private String value;

}
