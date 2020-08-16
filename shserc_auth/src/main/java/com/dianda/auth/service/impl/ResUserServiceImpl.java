package com.dianda.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dianda.auth.common.Constant;
import com.dianda.auth.dto.EditUserDto;
import com.dianda.auth.dto.mappers.IEditUserMapper;
import com.dianda.auth.entity.ResUser;
import com.dianda.auth.exceptions.GlobalException;
import com.dianda.auth.mapper.ResUserMapper;
import com.dianda.auth.service.IResUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.auth.util.basic.ObjectUtil;
import com.dianda.auth.util.basic.StringUtil;
import com.dianda.auth.vo.ResUserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;

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
	public ResUserVo findByPhrase( String phrase , Integer current , Integer size ) {
		IPage<ResUser> page = new Page<> ( current , size );
		QueryWrapper<ResUser> wrapper = new QueryWrapper<> ( );
		
		if ( ! StringUtil.IsNullOrEmpty ( phrase ) )
			wrapper = wrapper.like ( "user_name" , phrase );
		
		page = resUserMapper.selectPage ( page , wrapper.ne ( "is_deleted" , 1 ) );
		
		ResUserVo resUserVo = new ResUserVo ( );
		if ( ! ObjectUtil.isNull ( page ) ) {
			resUserVo.setUserList ( page.getRecords ( ) );
			resUserVo.setTotal ( page.getTotal ( ) );
			resUserVo.setCurrent ( current );
			resUserVo.setSize ( size );
		}
		
		return resUserVo;
	}
	
	
	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public ResUser edit( EditUserDto userDto )  {
		if ( ObjectUtil.isNull ( userDto ) )
			return null;
		
		// execute user mapping from dto
		ResUser user= IEditUserMapper.INSTANCE.mapFrom ( userDto );
		if ( StringUtil.IsNullOrEmpty ( user.getId () ) ) {
			resUserMapper.insert ( user );
		} else {
			resUserMapper.updateById ( user );
		}
		
		return user;
	}
	
	@Override
	public ResUser delete( String id ) {
		if ( StringUtil.IsNullOrEmpty ( id ) )
			return null;
		
		ResUser user = resUserMapper.selectById ( id );
		user.setIsDeleted ( Constant.Status.DELETED );
		
		int result = resUserMapper.updateById ( user );
		return user;
	}
	
}
