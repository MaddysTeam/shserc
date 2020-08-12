package com.dianda.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dianda.auth.entity.ResUser;
import com.dianda.auth.mapper.ResUserMapper;
import com.dianda.auth.service.IResUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.auth.util.basic.ObjectUtil;
import com.dianda.auth.util.basic.StringUtil;
import com.dianda.auth.vo.ResUserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author huachao
 * @since 2020-08-08
 */
@Service
public class ResUserServiceImpl extends ServiceImpl<ResUserMapper, ResUser> implements IResUserService {
	
	@Resource
	ResUserMapper resUserMapper;
	
	@Override
	public ResUserVo findByPhrase( String phrase , Integer current , Integer size ) {
		IPage<ResUser> page = new Page<> ( current , size );
		QueryWrapper<ResUser> wrapper =new QueryWrapper<> (  );
		
		if( !StringUtil.IsNullOrEmpty ( phrase ) )
			wrapper=wrapper.like ( "user_name",phrase );
		
		resUserMapper.selectPage ( page , wrapper);
		
		ResUserVo resUserVo=new ResUserVo ();
		if( !ObjectUtil.isNull ( page ) ) {
			resUserVo.setUserList ( page.getRecords ( ) );
			resUserVo.setTotal ( page.getTotal ( ) );
			resUserVo.setCurrent ( current );
			resUserVo.setSize ( size );
		}
		
		return resUserVo;
	}
	
	@Override
	public ResUser add( ResUser user ) {
		return null;
	}
	
	@Override
	public ResUser edit( ResUser user ) {
		return null;
	}
	
	@Override
	public ResUser delete( String id ) {
		return null;
	}
	
}
