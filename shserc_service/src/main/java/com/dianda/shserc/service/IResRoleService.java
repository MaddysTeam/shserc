package com.dianda.shserc.service;

import com.dianda.shserc.bean.RoleSelectParams;
import com.dianda.shserc.dto.EditRoleDto;
import com.dianda.shserc.entity.ResRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.vo.ResRoleVoList;

/**
 * role service
 *
 * @author huachao
 * @since 2020-08-12
 */
public interface IResRoleService extends IService<ResRole> {
	ResRoleVoList find( RoleSelectParams params);
	ResRole edit(EditRoleDto role);
	boolean delete(String id);
}
