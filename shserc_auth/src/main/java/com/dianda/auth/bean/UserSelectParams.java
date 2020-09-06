package com.dianda.auth.bean;

import lombok.Data;

@Data
public class UserSelectParams {
	long companyId;
	String phrase;
	int current;
	int size;
}
