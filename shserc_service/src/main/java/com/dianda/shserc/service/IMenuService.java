package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.bean.MenuSelectParams;
import com.dianda.shserc.dto.EditMenuDto;
import com.dianda.shserc.dto.EditMenuRoleDto;
import com.dianda.shserc.dto.EditStateDto;
import com.dianda.shserc.entity.Menu;
import com.dianda.shserc.vo.MenuVoList;
import com.dianda.shserc.validators.NotNull;

import javax.validation.Valid;

public interface IMenuService extends IService<Menu> {

	MenuVoList find(MenuSelectParams menuSelectParams);

	boolean edit(@Valid @NotNull EditMenuDto editMenuDto);

	boolean editMenuRole(@NotNull @Valid EditMenuRoleDto editMenuRoleDto);

	boolean setState(@NotNull @Valid EditStateDto editStateDto);

}
