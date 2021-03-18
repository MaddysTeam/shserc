package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.MenuSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditMenuDto;
import com.dianda.shserc.dto.EditMenuRoleDto;
import com.dianda.shserc.dto.mappers.IEditMenuMapper;
import com.dianda.shserc.dto.mappers.IEditMenuRoleMapper;
import com.dianda.shserc.entity.Menu;
import com.dianda.shserc.entity.MenuRole;
import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.mapper.MenuMapper;
import com.dianda.shserc.service.IMenuService;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.validators.NotNull;
import com.dianda.shserc.vo.MenuVo;
import com.dianda.shserc.vo.MenuVoList;
import com.dianda.shserc.vo.ResRoleVo;
import com.dianda.shserc.vo.mappers.IMenuVoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author hauchao
 * @title Menu Service Impl
 * @Date 2020/8/9 23:16
 * @Copyright 2019-2020
 */

@Service
@Validated
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
	
	/*
	TODO:  not  recommend to  use  cache manger directly ,you  can use  springboot  cache  instead
	 otherwise you want to  set  cache logic by yourself
	 */
	//	ICacheManager manager;

	@Resource
	MenuMapper mapper;

	@Override
	public MenuVoList find(MenuSelectParams menuSelectParams) {
		QueryWrapper<Menu> wrapper = new QueryWrapper<>();
		long roleId = menuSelectParams.getRoleId();
		if (roleId > 0) {
			wrapper.eq("role_id", roleId);
		}

		List<Menu> menuList = mapper.selectMenus(wrapper);
		List<MenuVo> voList = new ArrayList<>();
		for (Menu menu : menuList) {
			boolean exist = voList.stream().anyMatch(x -> x.getId() == menu.getId());
			MenuVo vo = null;
			if (!exist) {
				vo = IMenuVoMapper.INSTANCE.mapFrom(menu);
				voList.add(vo);
			} else {
				vo = voList.stream().filter(x -> x.getId() == menu.getId()).findFirst().get();
			}

			ResRoleVo roleVo = new ResRoleVo();
			roleVo.setId(vo.getRoleId());
			roleVo.setRoleName(vo.getRoleName());
			vo.getRoles().add(roleVo);
		}

		MenuVoList menuVoList = new MenuVoList();
		menuVoList.setListData(voList);

		return menuVoList;
	}

	@Override
	public boolean edit(@Valid @NotNull EditMenuDto editMenuDto) {
		Menu menu = IEditMenuMapper.INSTANCE.mapFrom(editMenuDto);
		Menu parentMenu = mapper.selectById(menu.getParentId()); // check  whether the parent menu is  exists or not
		if (ObjectUtil.isNull(parentMenu)) {
			throw new GlobalException(Constant.ErrorCode.LOGIC_RESULT_INVALID, Constant.Error.PARENT_MENU_IS_NOT_EXISTS);
		}

		boolean result;
		if (menu.isNewOne()) {
			result = mapper.insert(menu) > 0;
		} else {
			result = mapper.updateById(menu) >= 0;
		}

		return result;
	}

	@Override
	@Transactional(readOnly = true, rollbackFor = GlobalException.class)
	public boolean editMenuRole(@NotNull EditMenuRoleDto editMenuRoleDto) {
		MenuRole menuRole = IEditMenuRoleMapper.INSTANCE.mapFrom(editMenuRoleDto);

		// delete menu roles by menu id
		int result = mapper.deleteMenuRoles(menuRole);

		// re-add new menu role
		for (Map.Entry<Long, Long> entry : editMenuRoleDto.getMenuRoleMap().entrySet()) {
			menuRole.setMenuId(entry.getKey());
			menuRole.setRoleId(entry.getValue());
			result += mapper.insertMenuRole(menuRole);
		}

		if (result <= 0)
			throw new GlobalException(Constant.ErrorCode.LOGIC_RESULT_INVALID, Constant.Error.PARENT_MENU_IS_NOT_EXISTS);

		return true;
	}

}
