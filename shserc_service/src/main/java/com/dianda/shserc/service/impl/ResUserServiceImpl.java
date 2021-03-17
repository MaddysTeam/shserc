package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dianda.shserc.bean.UserSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditUserDto;
import com.dianda.shserc.dto.EditUserRoleDto;
import com.dianda.shserc.dto.mappers.IEditUserMapper;
import com.dianda.shserc.dto.mappers.IEditUserRoleMapper;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.entity.ResUserRole;
import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.mapper.ResRoleMapper;
import com.dianda.shserc.mapper.ResUserMapper;
import com.dianda.shserc.service.IResUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.basic.StringUtil;
import com.dianda.shserc.validators.NotNull;
import com.dianda.shserc.vo.ResRealVoList;
import com.dianda.shserc.vo.ResUserVo;
import com.dianda.shserc.vo.ResUserVoList;
import com.dianda.shserc.vo.mappers.IUserVoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huachao
 * @title ResUserServiceImpl
 * @Description 描述
 * @Date 2020/8/15 11:18
 * @Copyright 2019-2020
 */

@Service
public class ResUserServiceImpl extends ServiceImpl<ResUserMapper, ResUser> implements IResUserService {
	
	@Resource
	ResUserMapper mapper;
	
	@Override
	public ResUserVoList find( UserSelectParams params ) {
		if ( params == null )
			throw new GlobalException ( Constant.ErrorCode.PARAM_NULL_POINT_REFERENCE , Constant.Error.OBJECT_IS_REQUIRED );
		
		long companyId = params.getCompanyId ( );
		String phrase = params.getSearchPhrase ( );
		int current = params.getCurrent ( );
		int size = params.getSize ( );
		
		// build search condition
		
		QueryWrapper<ResUser> wrapper = new QueryWrapper<> ( );
		if ( ! StringUtil.isNullOrEmpty ( phrase ) )
			wrapper = wrapper.like ( "user_name" , phrase );
		
		if ( companyId > 0 ) {
			wrapper.eq ( "company_id" , companyId );
		}
		
		// get paged list data
		
		IPage<ResUser> page = new Page<> ( current , size );
		page = mapper.selectUsers ( page , wrapper );
		List<ResUser> listData = page.getRecords ( );
		List<ResUserVo> userVoListData = new ArrayList<> ( );
		
		for ( ResUser user : listData ) {
			userVoListData.add ( IUserVoMapper.INSTANCE.mapFrom ( user ) );
		}
		
		// return vo list data
		
		ResUserVoList resUserVoList = new ResUserVoList ( );
		resUserVoList.setListData ( userVoListData );
		resUserVoList.setTotal ( page.getTotal ( ) );
		resUserVoList.setCurrent ( current );
		resUserVoList.setSize ( size );
		
		return resUserVoList;
	}
	
	@Override
	@Transactional( readOnly = false, rollbackFor = Exception.class )
	public boolean edit( EditUserDto userDto ) {
		// execute user mapping from dto
		ResUser user = IEditUserMapper.INSTANCE.mapFrom ( userDto );
		if ( user.isNewOne ( ) ) {
			user.setPassword ( com.dianda.shserc.util.basic.EncoderUtil.SHA ( user.getPassword ( ) ) );
			return mapper.insert ( user ) > 0;
		} else {
			return mapper.updateById ( user ) >= 0;
		}
		
	}
	
	@Override
	public boolean delete( long id ) {
		if ( id <= 0 )
			return false;
		
		ResUser user = mapper.selectById ( id );
		user.setIsDeleted ( Constant.State.DELETED );
		int result = mapper.updateById ( user );
		
		return result > 1;
	}
	
	@Override
	public ResUserVo findById( long id ) {
		ResUser user = mapper.selectById ( id );
		ResUserVo vo = new ResUserVo ( );
		if ( ! ObjectUtil.isNull ( user ) )
			vo = IUserVoMapper.INSTANCE.mapFrom ( user );
		
		return vo;
	}
	
	@Override
	public ResUserVo getByNameAndPassword( String userName , String password ) {
		ResUserVo vo = new ResUserVo ( );
		ResUser user = mapper.selectOne (
				new QueryWrapper<ResUser> ( )
						.eq ( "user_name" , userName )
						.eq ( "password" , password ) );
		if ( ! ObjectUtil.isNull ( user ) )
			vo = IUserVoMapper.INSTANCE.mapFrom ( user );
		
		return vo;
	}
	
	@Override
	@Transactional( rollbackFor = GlobalException.class )
	public Boolean editUserRole( EditUserRoleDto model ) {
		ResUserRole userRole = IEditUserRoleMapper.INSTANCE.mapFrom ( model );
		userRole.setAddTime ( LocalDateTime.now ( ) );
		
		int result = mapper.deleteUserRole ( userRole );
		result += mapper.insertUserRole ( userRole );
		
		return result > 0;
	}
	
}


