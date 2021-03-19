package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MenuRole extends BaseEntity {

	private  long menuId;

	private  long roleId;

}
