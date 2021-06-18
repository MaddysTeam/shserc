package com.dianda.shsedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.basic.StringUtil;
import com.dianda.common.vo.RouteVo;
import com.dianda.shsedu.bean.PermissionSelectParams;
import com.dianda.shsedu.bean.RoleSelectParams;
import com.dianda.shsedu.common.ShseduConstant;
import com.dianda.shsedu.dto.mappers.IEditRoleMapper;
import com.dianda.shsedu.entity.Roles;
import com.dianda.shsedu.mapper.RoleMapper;
import com.dianda.shsedu.service.IPermissionService;
import com.dianda.shsedu.service.IRoleService;
import com.dianda.shsedu.vo.*;
import com.dianda.shsedu.vo.mappers.IRoleVoMapper;
import com.dianda.shsedu.dto.EditRoleDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("ShseduRoleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Roles> implements IRoleService {

	@Resource
	RoleMapper roleMapper;

	@Resource
	IPermissionService permissionService;


	@Override
	public RoleVoList findByPhrase(RoleSelectParams newsSelectParams) {
		QueryWrapper<Roles> wrapper = new QueryWrapper<>();
		String roleName = newsSelectParams.roleName;
		if (StringUtil.isNullOrEmpty(roleName)) {
			wrapper.like("role_name", roleName);
		}

		int current = newsSelectParams.getCurrent();
		int size = newsSelectParams.getSize();
		IPage<Roles> page = new Page<>(current, size);

		IPage<Roles> rolePage = roleMapper.selectRoles(page, wrapper);
		List<Roles> roleList = rolePage.getRecords();
		List<RoleVo> voList = new ArrayList<>();
		for (Roles role : roleList) {
			RoleVo vo = IRoleVoMapper.INSTANCE.mapFrom(role);
			voList.add(vo);
		}

		RoleVoList roleVoList = new RoleVoList();
		roleVoList.setListData(voList);

		return roleVoList;
	}

	@Override
	public boolean edit(EditRoleDto editRoleDto) {
		Roles roles = IEditRoleMapper.INSTANCE.mapFrom(editRoleDto);
		RoleVo roleVo = findById(roles.getId());
		roles.setAddUser(editRoleDto.getOperatorId());
		roles.setAddTime(editRoleDto.getOperateDate());
		if (roles.isNewOne() && ObjectUtil.isNull(roleVo)) {
			return roleMapper.insert(roles) > 0;
		} else {
			roles.setUpdateTime(editRoleDto.getOperateDate());
			roles.setUpdateUser(editRoleDto.getOperatorId());

			return roleMapper.updateById(roles) >= 0;
		}
	}

	@Override
	public RoleVo findById(long id) {
		Roles role = roleMapper.selectById(id);
		return IRoleVoMapper.INSTANCE.mapFrom(role);
	}

	@Override
	public PermissionVoList findPermissionsByRoleId(long id) {
		PermissionVoList voList = permissionService.findByPhrase(new PermissionSelectParams());
		if(voList.getTotal()<=0)
			throw new GlobalException("", "");
		RoleVo roleVo = findById(id);
		if(ObjectUtil.isNull(roleVo))
			throw new GlobalException("","");
		String[] permissionIds = roleVo.getPermissionIds().split(",");
		List<PermissionVo> results = new ArrayList<>();
		for (PermissionVo p : voList.getListData()) {
			for (String permissionId : permissionIds) {
				if (Long.parseLong(permissionId) == p.id) {
					results.add(p);
				}
			}
		}

		voList.setListData(results);
		return voList;
	}

	@Override
	public boolean hasPermissions(long id, long permissionId) {
		Roles role = roleMapper.selectById(id);
		return !ObjectUtil.isNull(role) && role.hasPermission(StringUtil.toString(permissionId));
	}

	@Override
	public RouteVo getRouteByRoleId(long id) {
		RouteVo routeVo = new RouteVo();
		PermissionVoList permissionVoList = findPermissionsByRoleId(id);
		if (!ObjectUtil.isNull(permissionVoList) && permissionVoList.getTotal() > 0) {
			long[] permissionIds = new long[permissionVoList.getListData().size()];
			int i = 0;
			for (PermissionVo vo : permissionVoList.getListData()) {
				permissionIds[i] = vo.id;
			}

			routeVo = permissionService.getRoute(ShseduConstant.Route.ROOT, permissionIds);
		}

		return routeVo;
	}

}
