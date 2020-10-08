package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.ResourceSelectParams;
import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.dto.mappers.IEditResourceMapper;
import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.mapper.ResourceMapper;
import com.dianda.shserc.service.IResourceService;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.basic.StringUtil;
import com.dianda.shserc.vo.ResourceVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * resource service
 *
 * @author huachao
 * @since 2020-10-01
 */

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
	
	@javax.annotation.Resource
	ResourceMapper mapper;
	
	@Override
	public ResourceVo find( ResourceSelectParams params ) {
		QueryWrapper<Resource> wrapper = new QueryWrapper<> ( );
		long deformityId = params.getDeformityId ( );
		String phrase = params.getPhrase ( );
		if ( ! StringUtil.IsNullOrEmpty ( phrase ) )
			wrapper = wrapper.like ( "title" , phrase );
		
		if ( deformityId > 0 ) {
			wrapper.eq ( "deformity" , deformityId );
		}
		
		//TODO: add more filter fields here
		
		int current = params.getCurrent ( );
		int size = params.getSize ( );
		IPage<Resource> page = new Page<> ( current , size );
		page = mapper.selectResources ( page , wrapper );
		List<Resource> resources = page.getRecords ( );
		
		ResourceVo vo = new ResourceVo ( );
		vo.setCurrent ( current );
		vo.setSize ( size );
		vo.setTotal ( page.getTotal ( ) );
		
		if ( ! ObjectUtil.isNull ( resources ) && resources.size ( ) > 0 )
			vo.setListData ( resources );
		
		return vo;
	}
	
	@Override
	public ResourceVo edit( EditResourceDto resourceDto ) {
		if ( ObjectUtil.isNull ( resourceDto ) )
			return null;
		
		// execute user mapping from dto
		Resource resource = IEditResourceMapper.INSTANCE.mapFrom ( resourceDto );
		if ( resource.isNewOne ( ) ) {
			mapper.insert ( resource );
		} else {
			mapper.updateById ( resource );
		}
		
		ResourceVo vo = null; // ResourceVoMapper.INSTANCE.mapFrom ( resource );
		return vo;
	}
	
	@Override
	public ResourceVo getById( long id ) {
		return null;
	}
	
	@Override
	public boolean setViewCount( long userId , long id ) {
		return false;
	}
	
}
