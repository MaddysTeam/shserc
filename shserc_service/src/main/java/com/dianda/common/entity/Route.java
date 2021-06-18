package com.dianda.common.entity;

import com.dianda.common.vo.RouteVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Route extends BaseEntity {
	private  String path;
	private  String name;
	private  int level;
	private  RouteVo[] children;
	private  String component;
	private String title;
	private String[] roles;
}
