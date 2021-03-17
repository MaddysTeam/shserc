package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.MenuSelectParams;
import com.dianda.shserc.dto.EditMenuDto;
import com.dianda.shserc.dto.EditMenuRoleDto;
import com.dianda.shserc.entity.Menu;
import com.dianda.shserc.mapper.MenuMapper;
import com.dianda.shserc.service.IMenuService;
import com.dianda.shserc.util.cache.manager.ICacheManager;
import com.dianda.shserc.validators.NotNull;
import com.dianda.shserc.vo.MenuVo;
import com.dianda.shserc.vo.MenuVoList;
import com.dianda.shserc.vo.ResRoleVo;
import com.dianda.shserc.vo.mappers.IMenuVoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	@Resource(name = "memoryCache")
	ICacheManager manager;

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
			boolean exist= voList.stream().anyMatch(x->x.getId()== menu.getId());
			MenuVo vo=null;
			if (!exist) {
				 vo = IMenuVoMapper.INSTANCE.mapFrom(menu);
				voList.add(vo);
			} else {
				 vo = IMenuVoMapper.INSTANCE.mapFrom(menu); //TODO  volist .find ()
				ResRoleVo roleVo = new ResRoleVo();
				roleVo.setId(vo.getRoleId());
				roleVo.setRoleName(vo.getRoleName());

				vo.getRoles().add(roleVo);
			}
		}

		MenuVoList menuVoList = new MenuVoList();
		menuVoList.setListData(voList);

		return menuVoList;
	}

	@Override
	public boolean edit(@Valid @NotNull EditMenuDto editMenuDto) {
		return false;
	}

	@Override
	public boolean editMenuRole(EditMenuRoleDto editMenuRoleDto) {
		return false;
	}

}
