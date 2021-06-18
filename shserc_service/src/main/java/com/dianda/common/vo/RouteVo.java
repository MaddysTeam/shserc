package com.dianda.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class RouteVo implements Serializable {
	private  long id;
	private  long parentId;
	private  String path;
	private  String name;
	private  int level;
	private ArrayList<RouteVo> children;
    private  String component;
    private String title;
    //private String[] roles;

    public RouteVo(){}

    public RouteVo(String path,String name,int level,String component,String title,ArrayList<RouteVo> children){}


}

