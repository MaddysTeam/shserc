package com.dianda.shserc.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserSelectParams extends BaseSelectParams {
	long companyId;
}


