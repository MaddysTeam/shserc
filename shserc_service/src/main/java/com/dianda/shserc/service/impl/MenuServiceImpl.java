package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.MenuSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditMenuDto;
import com.dianda.shserc.dto.EditMenuRoleDto;
import com.dianda.shserc.dto.EditStateDto;
import com.dianda.shserc.dto.mappers.IEditMenuMapper;
import com.dianda.shserc.entity.Menu;
import com.dianda.shserc.entity.MenuRole;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.shserc.mapper.MenuMapper;
import com.dianda.shserc.service.IMenuService;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.validators.NotNull;
import com.dianda.shserc.vo.MenuVo;
import com.dianda.shserc.vo.MenuVoList;
import com.dianda.shserc.vo.ResRoleVo;
import com.dianda.shserc.vo.mappers.IMenuVoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
	public MenuVoList find( MenuSelectParams menuSelectParams ) {
		QueryWrapper<Menu> wrapper = new QueryWrapper<> ( );
		long roleId = menuSelectParams.getRoleId ( );
		if ( roleId > 0 ) {
			wrapper.eq ( "role_id" , roleId );
		}
		
		//ArrayList<ResRoleVo> roleVoList = new ArrayList ( );
		List<Menu> menuList = mapper.selectMenus ( wrapper );
		List<MenuVo> voList = new ArrayList<> ( );
		for ( Menu menu : menuList ) {
			MenuVo vo = IMenuVoMapper.INSTANCE.mapFrom ( menu );
			vo.setRoles ( new ArrayList<> (  ) );
			
			boolean exist = voList.stream ( ).anyMatch ( x -> x.getId ( ) == menu.getId ( ) );
			if(!exist){
				voList.add ( vo );
			}
			else{
				vo = voList.stream ( ).filter ( x -> x.getId ( ) == menu.getId ( ) ).findFirst ( ).get ( );
			}
			
			if ( menu.getRoleId ( ) > 0 ) {
				ResRoleVo roleVo = new ResRoleVo ( );
				roleVo.setId ( menu.getRoleId ( ) );
				roleVo.setRoleName ( menu.getRoleName ( ) );
				vo.getRoles ().add ( roleVo );
			}
		}
		
		MenuVoList menuVoList = new MenuVoList ( );
		menuVoList.setListData ( voList );
		
		return menuVoList;
	}
	
	@Override
	public boolean edit( @Valid @NotNull EditMenuDto editMenuDto ) {
		Menu menu = IEditMenuMapper.INSTANCE.mapFrom ( editMenuDto );
		Menu parentMenu = mapper.selectById ( menu.getParentId ( ) ); // check  whether the parent menu is  exists or not
		if ( menu.getParentId ( ) > 0 && ObjectUtil.isNull ( parentMenu ) ) {
			throw new GlobalException ( Constant.ErrorCode.LOGIC_RESULT_INVALID , Constant.Error.PARENT_MENU_IS_NOT_EXISTS );
		}
		
		boolean result;
		if ( menu.isNewOne ( ) ) {
			menu.setAddTime ( LocalDateTime.now ( ) );
			result = mapper.insert ( menu ) > 0;
		} else {
			menu.setUpdateTime ( LocalDateTime.now ( ) );
			result = mapper.updateById ( menu ) >= 0;
		}
		
		return result;
	}
	
	@Override
	@Transactional( rollbackFor = GlobalException.class )
	public boolean editMenuRole( @NotNull @Valid List<EditMenuRoleDto> editMenuRoleDto ) {
		//get menu id
		MenuRole menuRole = new MenuRole ( );
		menuRole.setMenuId ( editMenuRoleDto.get ( 0 ).getMenuId ( ) );
		
		// delete menu roles by menu id
		int result = mapper.deleteMenuRoles ( menuRole );
		
		// re-add new menu role
		for ( EditMenuRoleDto item : editMenuRoleDto ) {
			menuRole.setMenuId ( item.getMenuId ( ) );
			menuRole.setRoleId ( item.getRoleId ( ) );
			result += mapper.insertMenuRole ( menuRole );
		}
		
		if ( result <= 0 )
			throw new GlobalException ( Constant.ErrorCode.LOGIC_RESULT_INVALID , Constant.Error.EDIT_FAILURE );
		
		return true;
	}
	
	@Override
	public boolean setState( @NotNull @Valid EditStateDto editStateDto ) {
		Menu menu = new Menu ( );
		menu.setId ( editStateDto.getTargetId ( ) );
		menu.setStateId ( editStateDto.getStateId ( ) );
		
		return mapper.updateById ( menu ) >= 0;
	}
	
}
