package com.dianda.shserc.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class BulletinSelectParams extends BaseSelectParams {
	private long  typeId;
}
