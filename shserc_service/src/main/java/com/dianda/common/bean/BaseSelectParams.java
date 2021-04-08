package com.dianda.common.bean;

import lombok.Data;

import java.util.Map;

@Data
public class BaseSelectParams{
	String searchPhrase;
	Map<String,String> orderPhrases;
	int current;
	int size;
}

