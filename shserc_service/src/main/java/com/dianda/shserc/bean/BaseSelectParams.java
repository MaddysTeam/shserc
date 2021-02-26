package com.dianda.shserc.bean;

import lombok.Data;

import java.util.Map;

@Data
public class BaseSelectParams{
	String phrase;
	Map<String,String> orderPhrases;
	int current;
	int size;
}


