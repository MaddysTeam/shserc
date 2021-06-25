package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dianda.common.util.basic.DateTimeUtil;
import com.dianda.shserc.bean.RoleSelectParams;
import com.dianda.shserc.dto.EditRoleDto;
import com.dianda.shserc.dto.mappers.IEditRoleMapper;
import com.dianda.shserc.entity.ResRole;
import com.dianda.shserc.mapper.ResRoleMapper;
import com.dianda.shserc.service.IResRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.basic.StringUtil;
import com.dianda.shserc.vo.ResRoleVo;
import com.dianda.shserc.vo.ResRoleVoList;
import com.dianda.shserc.vo.mappers.IRoleVoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色服务实现类
 *
 * @author huachao
 * @since 2020-08-12
 */
@Service
public class ResRoleServiceImpl extends ServiceImpl<ResRoleMapper, ResRole> implements IResRoleService {
	
	@Resource
	ResRoleMapper mapper;
	
	@Override
	public ResRoleVoList find( RoleSelectParams params ) {
		QueryWrapper<ResRole> queryWrapper = new QueryWrapper<> ( );
		
		String phrase = params.getSearchPhrase ( );
		if ( ! StringUtil.isNullOrEmpty ( phrase ) ) {
			queryWrapper = queryWrapper.and ( wrapper ->
					wrapper.like ( "role_name" , phrase )
							.or ( )
							.like ( "description" , phrase ) );
		}
		
		// get paged list data
		
		int current = params.getCurrent ( );
		int size = params.getSize ( );
		IPage<ResRole> page = new Page<> ( current , size );
		page = mapper.selectRole ( page , queryWrapper );
		List<ResRole> listData = page.getRecords ( );
		List<ResRoleVo> listVoData = new ArrayList<> ( );
		for ( ResRole resRole : listData ) {
			listVoData.add ( IRoleVoMapper.INSTANCE.mapFrom ( resRole ) );
		}
		
		ResRoleVoList resRoleVoList = new ResRoleVoList ( );
		resRoleVoList.setListData ( listVoData );
		resRoleVoList.setCurrent ( current );
		resRoleVoList.setSize ( size );
		resRoleVoList.setTotal ( page.getTotal ( ) );
		
		return resRoleVoList;
	}
	
	@Override
	public ResRoleVo findById( long id ) {
		QueryWrapper<ResRole> queryWrapper = new QueryWrapper<> ( );
		queryWrapper.eq ( "id" , id );
		ResRole resRole = mapper.selectOne ( queryWrapper );
		ResRoleVo vo = ObjectUtil.isNull ( resRole ) ? null : IRoleVoMapper.INSTANCE.mapFrom ( resRole );
		
		return vo;
	}
	
	@Override
	public boolean edit( EditRoleDto roleDto ) {
		ResRole resRole = IEditRoleMapper.INSTANCE.mapFrom ( roleDto );
		if ( resRole.isNewOne ( ) ) {
			resRole.setAddUser ( roleDto.getOperatorId ( ) );
			resRole.setAddTime ( DateTimeUtil.now ( ) );
			
			return mapper.insert ( resRole ) > 0;
		} else {
			resRole.setUpdateUser ( roleDto.getOperatorId ( ) );
			resRole.setUpdateTime ( DateTimeUtil.now ( ) );
			
			return mapper.updateById ( resRole ) > 0;
		}
	}
	
	@Override
	public boolean delete( String id ) {
		return false;
	}
	
}
