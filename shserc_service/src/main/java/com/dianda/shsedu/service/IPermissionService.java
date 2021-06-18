package com.dianda.shsedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.common.service.IRouteService;
import com.dianda.common.vo.RouteVo;
import com.dianda.shsedu.bean.PermissionSelectParams;
import com.dianda.shsedu.dto.EditPermissionDto;
import com.dianda.shsedu.entity.Permission;
import com.dianda.shsedu.vo.PermissionVo;
import com.dianda.shsedu.vo.PermissionVoList;

public interface IPermissionService extends IRouteService, IService<Permission> {

	PermissionVoList findByPhrase(PermissionSelectParams permissionSelectParams);

	boolean edit(EditPermissionDto editPermissionDto);

	PermissionVo findById(long id );
}
