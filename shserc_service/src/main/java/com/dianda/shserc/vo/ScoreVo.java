package com.dianda.shserc.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huachao
 * @title target score vo
 * @Description
 * for two dimensions:
 * 1 . vo model for target score, for example :  resource score , package score
 * 2.  vo model for score details for example :  target scored by someone
 * @Date 2021/4/18 17:14
 * @Copyright 2019-2020
 */

@Data
public class ScoreVo implements Serializable {
	
	private long targetId;
	
	private  long userId;
	
	private  long scoreCount;
	
	private int score;
	
}

