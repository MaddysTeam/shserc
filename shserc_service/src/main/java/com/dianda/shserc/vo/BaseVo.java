package com.dianda.shserc.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseVo<T> implements Serializable {
	Integer current;
	Integer size;
	Long total;

	List<T> listData;
}
