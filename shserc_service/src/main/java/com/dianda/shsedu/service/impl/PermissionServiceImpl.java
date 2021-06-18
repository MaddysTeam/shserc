package com.dianda.shsedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.common.entity.Route;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.basic.StringUtil;
import com.dianda.common.vo.RouteVo;
import com.dianda.shsedu.bean.PermissionSelectParams;
import com.dianda.shsedu.dto.EditPermissionDto;
import com.dianda.shsedu.dto.mappers.IEditPermissionMapper;
import com.dianda.shsedu.dto.mappers.IEditRoleMapper;
import com.dianda.shsedu.entity.Permission;
import com.dianda.shsedu.mapper.PermissionMapper;
import com.dianda.shsedu.service.IPermissionService;
import com.dianda.shsedu.vo.PermissionVo;
import com.dianda.shsedu.vo.PermissionVoList;
import com.dianda.shsedu.vo.mappers.IPermissionVoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("ShseduPermissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

	@Resource
	PermissionMapper permissionMapper;

	@Override
	public PermissionVoList findByPhrase(PermissionSelectParams permissionSelectParams) {
		QueryWrapper<Permission> wrapper = new QueryWrapper<>();
		String permissionName = permissionSelectParams.permissionName;
		if (StringUtil.isNullOrEmpty(permissionName)) {
			wrapper.like("name", permissionName);
		}

		int current = permissionSelectParams.getCurrent();
		int size = permissionSelectParams.getSize();
		IPage<Permission> page = new Page<>(current, size);

		IPage<Permission> rolePage = permissionMapper.selectPermissions(page, wrapper);
		List<Permission> permissionList = rolePage.getRecords();
		List<PermissionVo> voList = new ArrayList<>();
		for (Permission permission : permissionList) {
			PermissionVo vo = IPermissionVoMapper.INSTANCE.mapFrom(permission);
			voList.add(vo);
		}

		PermissionVoList permissionVoList = new PermissionVoList();
		permissionVoList.setListData(voList);

		return permissionVoList;
	}

	@Override
	public boolean edit(EditPermissionDto editPermissionDto) {
		Permission permission = IEditPermissionMapper.INSTANCE.mapFrom(editPermissionDto);
		PermissionVo permissionVo = findById(permission.getId());
		permission.setAddUser(editPermissionDto.getOperatorId());
		permission.setAddTime(editPermissionDto.getOperateDate());
		if (permission.isNewOne() && ObjectUtil.isNull(permissionVo)) {
			return permissionMapper.insert(permission) > 0;
		} else {
			permission.setUpdateTime(editPermissionDto.getOperateDate());
			permission.setUpdateUser(editPermissionDto.getOperatorId());

			return permissionMapper.updateById(permission) >= 0;
		}
	}

	@Override
	public PermissionVo findById(long id) {
		Permission permission = permissionMapper.selectById(id);

		return IPermissionVoMapper.INSTANCE.mapFrom(permission);
	}

	@Override
	public RouteVo getRoute(long rootId,long[] ownIds) {
		Permission permission = permissionMapper.selectById(rootId);
		RouteVo routeVo = new RouteVo();

		return routeVo;
	}

	@Override
	public List<RouteVo> getRoutes(long parentId) {
		return null;
	}

	private RouteVo recursiveMapping(long parentId , List<RouteVo> source ) {
		boolean isExists = source.stream ( ).anyMatch ( x -> x.getId ( ) == parentId );
		if ( ! isExists )
			return new RouteVo ( );

		RouteVo routeVo = source.stream ( ).filter ( x -> x.getId ( ) == parentId ).findFirst ( ).get ( );
		ArrayList<RouteVo> children = new ArrayList<> ( );
		//RouteVo vo = new RouteVo ( routeVo.getId ( ) , routeVo.getName ( ) , children );

		if ( ObjectUtil.isNull ( source ) || source.isEmpty ( ) )
			return routeVo;

		List<RouteVo> childrenList = source.stream ( ).filter ( x -> x.getParentId ( ) == parentId ).collect ( Collectors.toList ( ) );
		for ( RouteVo item : childrenList ) {
			children.add (recursiveMapping ( item.getId ( ) , source ) );
		}

		return routeVo;
	}
}
