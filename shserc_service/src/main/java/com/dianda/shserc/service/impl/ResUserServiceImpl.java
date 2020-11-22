package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dianda.shserc.bean.UserSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditUserDto;
import com.dianda.shserc.dto.mappers.IEditUserMapper;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.entity.ResUserRole;
import com.dianda.shserc.mapper.ResUserMapper;
import com.dianda.shserc.service.IResUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.basic.StringUtil;
import com.dianda.shserc.vo.ResUserVo;
import com.dianda.shserc.vo.mappers.IUserVoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
	ResUserMapper resUserMapper;
	
	@Override
	public ResUserVo find( UserSelectParams params ) {
		if ( params == null ) return new ResUserVo ( );
		
		long companyId = params.getCompanyId ( );
		String phrase = params.getPhrase ( );
		int current = params.getCurrent ( );
		int size = params.getSize ( );
		
		IPage<ResUser> page = new Page<> ( current , size );
		QueryWrapper<ResUser> wrapper = new QueryWrapper<> ( );
		ResUserVo resUserVo = new ResUserVo ( );
		
		if ( ! StringUtil.IsNullOrEmpty ( phrase ) )
			wrapper = wrapper.like ( "user_name" , phrase );
		
		if ( companyId > 0 ) {
			wrapper.eq ( "company_id" , companyId );
		}
		
		IPage<ResUser> pageData = resUserMapper.selectUsers ( page , wrapper );
		List<ResUser> users = pageData.getRecords ( );
		resUserVo.setListData ( users );
		
		if ( ! ObjectUtil.isNull ( users ) && users.size ( ) > 0 ) {
			resUserVo.setTotal ( page.getTotal ( ) );
			resUserVo.setCurrent ( current );
			resUserVo.setSize ( size );
		}
		
		return resUserVo;
	}
	
	@Override
	@Transactional( readOnly = false, rollbackFor = Exception.class )
	public ResUserVo edit( EditUserDto userDto ) {
		if ( ObjectUtil.isNull ( userDto ) )
			return null;
		
		// execute user mapping from dto
		ResUser user = IEditUserMapper.INSTANCE.mapFrom ( userDto );
		if ( user.isNewOne ( ) ) {
			resUserMapper.insert ( user );
		} else {
			resUserMapper.updateById ( user );
		}
		
		ResUserVo vo = IUserVoMapper.INSTANCE.mapFrom ( user );
		return vo;
	}
	
	@Override
	public ResUserVo delete( long id ) {
		if ( id <= 0 )
			return null;
		
		ResUser user = resUserMapper.selectById ( id );
		user.setIsDeleted ( Constant.Status.DELETED );
		int result = resUserMapper.updateById ( user );
		
		ResUserVo vo = IUserVoMapper.INSTANCE.mapFrom ( user );
		return vo;
	}
	
	@Override
	public ResUserVo getById( long id ) {
		ResUser user = resUserMapper.selectById ( id );
		ResUserVo vo = new ResUserVo ( );
		if ( ! ObjectUtil.isNull ( user ) )
			vo = IUserVoMapper.INSTANCE.mapFrom ( user );
		
		return vo;
	}
	
	@Override
	public ResUserVo getByNameAndPassword( String userName , String password ) {
		ResUserVo vo = new ResUserVo ( );
		ResUser user = resUserMapper.selectOne (
				new QueryWrapper<ResUser> ( )
						.eq ( "user_name" , userName )
						.eq ( "password" ,password ) );
		if ( ! ObjectUtil.isNull ( user ) )
			vo = IUserVoMapper.INSTANCE.mapFrom ( user );
		
		return vo;
	}
	
	@Override
	public Boolean addUserRole( long userId , long roleId ) {
		ResUserRole userRole = new ResUserRole ( );
		userRole.setRoleId ( roleId );
		userRole.setUserId ( userId );
		return resUserMapper.addUserRole ( userRole ) > 0;
	}
	
	@Override
	public Boolean deleteUserRole( long userId , long roleId ) {
		return null;
	}
	
}
