package com.dianda.shserc.bean;

import lombok.Data;

@Data
public class UserSelectParams {
	long companyId;
	String phrase;
	int current;
	int size;
}
