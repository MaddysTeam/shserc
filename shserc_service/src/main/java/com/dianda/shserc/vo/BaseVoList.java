package com.dianda.shserc.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseVoList<T> implements Serializable {
	Integer current;
	Integer size;
	Long total;

	List<T> listData;
}
