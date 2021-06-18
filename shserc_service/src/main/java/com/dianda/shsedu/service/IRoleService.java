package com.dianda.shsedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.common.vo.RouteVo;
import com.dianda.shsedu.bean.RoleSelectParams;
import com.dianda.shsedu.entity.Roles;
import com.dianda.shsedu.vo.PermissionVoList;
import com.dianda.shsedu.vo.RoleVo;
import com.dianda.shsedu.vo.RoleVoList;
import com.dianda.shsedu.dto.EditRoleDto;


public interface IRoleService extends IService<Roles> {

	RoleVoList findByPhrase(RoleSelectParams roleSelectParams);

	boolean edit(EditRoleDto editRoleDto);

	RoleVo findById(long id );

	PermissionVoList findPermissionsByRoleId(long id);

	boolean hasPermissions(long id,long permissionId);

	RouteVo  getRouteByRoleId(long id);
}
