package com.dianda.auth.service.impl;

import com.dianda.auth.dto.EditCompanyDto;
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
 * <p>
 * 服务实现类
 * </p>
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
		ResCompanyVo companyVo = recursiveMapping ( 99 , results );
		
		return companyVo;
	}
	
	@Override
	public ResCompanyVo findByParentId( long parentId ) {
		return null;
	}
	
	@Override
	public ResCompany edit( EditCompanyDto company ) {
		return null;
	}
	
	@Override
	public ResCompany delete( long id ) {
		return null;
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
