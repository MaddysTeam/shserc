package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditCompanyDto;
import com.dianda.shserc.dto.mappers.IEditCompanyMapper;
import com.dianda.shserc.entity.ResCompany;
import com.dianda.common.exceptions.ExceptionType;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.shserc.mapper.ResCompanyMapper;
import com.dianda.shserc.service.IResCompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.shserc.vo.ResCompanyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * company service
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
		ResCompanyVo companyVo = recursiveMapping ( Constant.ThisApp.ROOT_ID , results );
		
		return companyVo;
	}
	
	@Override
	public ResCompanyVo findChildren( long parentId ) {
		QueryWrapper<ResCompany> wrapper=new QueryWrapper<>();
		List<ResCompany> childrenIncludeParent= resCompanyMapper
				.selectList(wrapper
						.eq("parent_Id", parentId)
						.or()
						.eq("id", parentId)
				);

		ResCompanyVo parentVo=new ResCompanyVo();
		for(ResCompany item : childrenIncludeParent){
			if(item.getId()==parentId){
				parentVo.setId(parentId);
				parentVo.setLabel(item.getCompanyName());
			}
			else{
				parentVo.getChildren().add (
						new ResCompanyVo(item.getId(),
								         item.getCompanyName(),
								 null));
			}
		}

		return parentVo;
	}
	
	@Override
	public ResCompany edit( EditCompanyDto companyDto,long userId ) {
		if ( ObjectUtil.isNull ( companyDto ) )
			return null;
		
		long parentId = companyDto.getParentId ( );
		ResCompany parent = resCompanyMapper.selectById ( parentId );
		if ( ObjectUtil.isNull ( parent ) )
			throw new GlobalException ( ExceptionType.SERVER_ERROR , "" );
		
		ResCompany o = IEditCompanyMapper.INSTANCE.mapFrom ( companyDto );
		
		int result = 0;
		if ( o.isNewOne ( ) ) {
			o.setAddTime (LocalDateTime.now ());
			o.setAddUser (userId  );
			result = resCompanyMapper.insert ( o );
		} else {
			o.setUpdateTime ( LocalDateTime.now () );
			o.setUpdateUser ( userId );
			result = resCompanyMapper.updateById ( o );
		}
		if ( result <= 0 )
			throw new GlobalException ( ExceptionType.SERVER_ERROR , "" );
		
		return o;
	}
	
	@Override
	public ResCompany delete( long id ) {
		if ( id <= 0 )
			return null;
		
		ResCompany company = resCompanyMapper.selectById ( id );
		//TODO: if this used by user and resource then cannot been deleted
		
		company.setIsDeleted ( Constant.State.DELETED );
		resCompanyMapper.updateById ( company );
		
		return company;
	}
	
	
	private ResCompanyVo recursiveMapping( long parentId , List<ResCompany> source ) {
		boolean isExists = source.stream ( ).anyMatch ( x -> x.getId ( ) == parentId );
		if ( ! isExists )
			return new ResCompanyVo ( );
		
		ResCompany company = source.stream ( ).filter ( x -> x.getId ( ) == parentId ).findFirst ( ).get ( );
		ArrayList<ResCompanyVo> children = new ArrayList<> ( );
		ResCompanyVo vo = new ResCompanyVo ( company.getId ( ) , company.getCompanyName ( ) , children );
		
		if ( ObjectUtil.isNull ( source ) || source.isEmpty ( ) )
			return vo;
		
		List<ResCompany> childrenList = source.stream ( ).filter ( x -> x.getParentId ( ) == parentId ).collect ( Collectors.toList ( ) );
		for ( ResCompany item : childrenList ) {
			children.add (recursiveMapping ( item.getId ( ) , source ) );
		}
		
		return vo;
	}
}
