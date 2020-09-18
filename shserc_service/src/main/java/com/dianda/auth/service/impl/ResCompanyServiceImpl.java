package com.dianda.auth.service.impl;

import com.dianda.auth.common.Constant;
import com.dianda.auth.dto.EditCompanyDto;
import com.dianda.auth.dto.mappers.IEditCompanyMapper;
import com.dianda.auth.entity.ResCompany;
import com.dianda.auth.exceptions.GlobalException;
import com.dianda.auth.mapper.ResCompanyMapper;
import com.dianda.auth.service.IResCompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.auth.util.basic.ObjectUtil;
import com.dianda.auth.vo.ResCompanyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * company service
 *
 *
 * @author huachao
 * @since 2020-08-17
 */
@Service
public class ResCompanyServiceImpl extends ServiceImpl<ResCompanyMapper, ResCompany> implements IResCompanyService {
	
	@Resource
	ResCompanyMapper resCompanyMapper;
	
	@Override
	public ResCompanyVo find( ) {
		//  step 1 : find all
		//  step 2: return hierarchy data
		
		List<ResCompany> results = resCompanyMapper.selectList ( null );
		ResCompanyVo companyVo = recursiveMapping (Constant.ThisApp.ROOT_ID , results );
		
		return companyVo;
	}
	
	@Override
	public ResCompanyVo findByParentId( long parentId ) {
		return null;
	}
	
	@Override
	public ResCompany edit( EditCompanyDto companyDto ) {
		if(ObjectUtil.isNull(companyDto))
			return null;

		long parentId=companyDto.getParentId();
		resCompanyMapper。s

		ResCompany o= IEditCompanyMapper.INSTANCE.mapFrom(companyDto);

		if(o.isNewOne()){
			resCompanyMapper.insert(o);
		}
		else{
			resCompanyMapper.updateById(o);
		}

		return o;
	}
	
	@Override
	public ResCompany delete( long id ) {
		if (id <= 0)
			return null;

		ResCompany company= resCompanyMapper.selectById(id);
		//TODO: if this used by user and resource then cannot been deleted

		company.setIsDeleted(Constant.Status.DELETED);
		resCompanyMapper.updateById(company);

		return company;
	}
	
	
	private ResCompanyVo recursiveMapping( long parentId , List<ResCompany> source ) {
		boolean isExists = source.stream ( ).anyMatch ( x -> x.getId ( ) == parentId );
		if ( ! isExists )
			return new ResCompanyVo();
		
		ResCompany company = source.stream ( ).filter ( x -> x.getId ( ) == parentId ).findFirst ( ).get ( );
		Map<Long, ResCompanyVo> childrenMap = new HashMap<> ( );
		ResCompanyVo vo = new ResCompanyVo ( company.getId ( ) , company.getCompanyName ( ) , childrenMap );
		
		if ( ObjectUtil.isNull ( source ) || source.isEmpty ( ) )
			return vo;
		
		List<ResCompany> childrenList = source.stream ( ).filter ( x -> x.getParentId ( ) == parentId ).collect ( Collectors.toList ( ) );
		for ( ResCompany item : childrenList ) {
			childrenMap.put ( item.getId ( ) , recursiveMapping ( item.getId ( ) , source ) );
		}
		
		return vo;
	}
}
