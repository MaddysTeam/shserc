package com.dianda.shsedu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ShseduUser extends BaseEntity {

	private String userName;

	private String password;

	private long districtId;

	private String realName;

	private String mobile;

	private String email;

	private long roleId;

	private String permissionIds;

	@TableField(exist = false)
	private String roleIds;
	@TableField(exist = false)
	private List<Columns> ownedColumns;

	public boolean hasPermission(String permissionId) {
		return permissionIds.indexOf(permissionId) >= 0;
	}

	public boolean hasInRole(long roleId) {
		return roleId == this.roleId;
	}
}
