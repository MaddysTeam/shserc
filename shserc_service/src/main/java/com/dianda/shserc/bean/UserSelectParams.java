package com.dianda.shserc.bean;

import com.dianda.common.bean.BaseSelectParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserSelectParams extends BaseSelectParams {
	long companyId;
}


