package com.dianda.shserc.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RealSelectParams extends BaseSelectParams{
	long companyId;
}
