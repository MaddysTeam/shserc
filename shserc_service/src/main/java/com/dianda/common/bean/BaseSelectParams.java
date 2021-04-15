package com.dianda.common.bean;

import com.dianda.common.util.basic.MapperUtil;
import com.dianda.common.util.basic.StringUtil;
import com.dianda.shserc.common.Constant;
import lombok.Data;


import java.util.Map;

@Data
public class BaseSelectParams {
	String searchPhrase;
	Map<String, String> orderPhrases;
	int current;
	int size;
	
	public String translateOrderPhrase( String frontendOrderPhrase ) {
		Map<String, String> orderPhraseMapping = Constant.OrderPhraseMapping.getOrderPhraseMapping ( );
		String value=MapperUtil.FindValueByKey (orderPhraseMapping,  frontendOrderPhrase);

		return value;
	}
	
}


