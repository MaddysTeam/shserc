package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.basic.StringUtil;
import com.dianda.common.util.cache.dictionary.DictionaryCache;
import com.dianda.common.util.logger.system.SystemLog;
import com.dianda.common.validators.NotNull;
import com.dianda.shserc.bean.CroResourceSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditCroResourceDto;
import com.dianda.shserc.dto.ResourceAuditDto;
import com.dianda.shserc.dto.mappers.ICroResourceAuditMapper;
import com.dianda.shserc.dto.mappers.IEditCroResourceMapper;
import com.dianda.shserc.entity.CroResource;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.mapper.CroResourceMapper;
import com.dianda.shserc.service.ICroResourceService;
import com.dianda.shserc.vo.*;
import com.dianda.shserc.vo.mappers.ICroResourceVoMapper;
import com.dianda.shserc.vo.mappers.IResourceScoreVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * resource service
 *
 * @author huachao
 * @since 2020-10-01
 */

@Service
@Validated
public class CroResourceServiceImpl extends ServiceImpl<CroResourceMapper, CroResource> implements ICroResourceService {
	
	@javax.annotation.Resource
	CroResourceMapper mapper;
	
	DictionaryCache cache;
	
	@Autowired
	public CroResourceServiceImpl( DictionaryCache cache ) {
		this.cache = cache;
		this.cache.setCacheFromService ( 0 );
	}
	
	@Override
	public CroResourceVoList find( CroResourceSelectParams params ) {
		QueryWrapper<CroResource> wrapper = new QueryWrapper<> ( );
		long userId = params.getUserId ( );
		long deformityId = params.getDeformityId ( );
		long stateId = params.getStateId ( );
		long stageId = params.getStageId ( );
		long subjectId = params.getSubjectId ( );
		long importSourceId = params.getImportSourceId ( );
		long domainId = params.getDomainId ( );
		long gradeId = params.getGradeId ( );
		long resourceTypeId = params.getResourceTypeId ( );
		long createTypeId=params.getCreateTypeId ();
		Map<String, String> orderPhrases = params.getOrderPhrases ( );
		String phrase = params.getSearchPhrase ( );
		
		// filter fields
		
		wrapper = wrapper.eq ( "is_deleted" , 0 );
		if ( userId > 0 ) {
			wrapper = wrapper.eq ( "user_id" , userId );
		}
		if ( deformityId > 0 ) {
			wrapper = wrapper.eq ( "deformity_Id" , deformityId );
		}
		if ( stateId > 0 ) {
			wrapper = wrapper.eq ( "state_Id" , stateId );
		}
		if ( stageId > 0 ) {
			wrapper = wrapper.eq ( "stage_id" , stageId );
		}
		if ( subjectId > 0 ) {
			wrapper = wrapper.eq ( "subject_id" , subjectId );
		}
		if ( importSourceId > 0 ) {
			wrapper = wrapper.eq ( "import_source_id" , importSourceId );
		}
		if ( domainId > 0 ) {
			wrapper = wrapper.eq ( "domain_id" , domainId );
		}
		if ( resourceTypeId > 0 ) {
			wrapper = wrapper.eq ( "resource_type_id" , resourceTypeId );
		}
		if ( gradeId > 0 ) {
			wrapper = wrapper.eq ( "grade_id" , stageId );
		}
		if(createTypeId>0){
			wrapper = wrapper.eq ( "create_type_id" , createTypeId );
		}
		
		if ( ! StringUtil.isNullOrEmpty ( phrase ) )
			wrapper = wrapper.like ( "title" , phrase );
		
		// order phrase
		if ( ! ObjectUtil.isNull ( orderPhrases ) && orderPhrases.size ( ) > 0 ) {
			for ( Map.Entry<String, String> entry : orderPhrases.entrySet ( ) ) {
				String orderPhrase = params.translateOrderPhrase ( entry.getKey ( ) );
				String direction = entry.getValue ( );
				if ( direction.equals ( Constant.OrderDirection.ASC ) ) {
					wrapper = wrapper.orderByAsc ( orderPhrase );
				} else if ( direction.equals ( Constant.OrderDirection.DESC ) ) {
					wrapper = wrapper.orderByDesc ( orderPhrase );
				}
			}
		} else {
			wrapper = wrapper.orderByDesc ( "id" );
		}
		
		// get paged list data
		
		int current = params.getCurrent ( );
		int size = params.getSize ( );
		IPage<CroResource> page = new Page<> ( current , size );
		page = mapper.selectCroResources ( page , wrapper );
		List<CroResource> resources = page.getRecords ( );
		List<CroResourceVo> croResourceVoList = new ArrayList<> ( );
		
		// translate
		
		for ( CroResource res : resources ) {
			CroResource.dictTranslate ( res , cache ); // 翻译字典
			CroResourceVo vo = ICroResourceVoMapper.INSTANCE.mapFrom ( res );
			vo.setGrade ( res.getGrade ( ) );
			vo.setSubject ( res.getSubject ( ) );
			croResourceVoList.add ( vo );
		}
		
		// get vo list data
		
		CroResourceVoList voList = new CroResourceVoList ( );
		voList.setCurrent ( current );
		voList.setSize ( size );
		voList.setTotal ( page.getTotal ( ) );
		
		if ( ! ObjectUtil.isNull ( resources ) && resources.size ( ) > 0 )
			voList.setListData ( croResourceVoList );
		
		return voList;
	}
	
	@Override
	public boolean edit( @Valid @NotNull EditCroResourceDto editCroResourceDto ) {
		CroResource croResource = IEditCroResourceMapper.INSTANCE.mapFrom ( editCroResourceDto );
		CroResourceVo croResourceVo = findById ( croResource.getId ( ) );
		if ( croResource.isNewOne ( ) && ObjectUtil.isNull ( croResourceVo ) ) {
			croResource.setAddTime ( editCroResourceDto.getOperateDate ( ) );
			croResource.setAddUser ( editCroResourceDto.getId ( ) );
			
			return mapper.insert ( croResource ) > 0;
		} else {
			croResource.setUpdateTime ( editCroResourceDto.getOperateDate ( ) );
			croResource.setUpdateUser ( editCroResourceDto.getId ( ) );
			
			return mapper.updateById ( croResource ) >= 0;
		}
	}
	
	@Override
	public boolean audit( @Valid @NotNull ResourceAuditDto model ) {
		CroResource croResource = ICroResourceAuditMapper.INSTANCE.mapFrom ( model );
		croResource.setStateId ( model.getAuditResult ( ) ? Constant.State.AUDITSUCCESS : Constant.State.AUDITFALURE );
		
		return mapper.updateById ( croResource ) >= 0;
	}
	
	@Override
	public CroResourceVo findById( long id ) {
		try {
			CroResource croResource = mapper.selectById ( id );
			CroResource.dictTranslate ( croResource , cache ); // 翻译字典
			CroResourceVo vo = ICroResourceVoMapper.INSTANCE.mapFrom ( croResource );
			vo.setGrade ( croResource.getGrade ( ) );
			vo.setSubject ( croResource.getSubject ( ) );
			
			return vo;
		} catch ( Exception e ) {
			return null;
		}
	}
	
	@Override
	@Transactional( readOnly = false, rollbackFor = GlobalException.class )
	@SystemLog()
	public boolean addViewCount( @Valid @NotNull ResourceOperation resourceOperation ) {
		CroResource croResource = mapper.selectById ( resourceOperation.getResourceId ( ) );
		if ( ObjectUtil.isNull ( croResource ) ) {
			return false;
		}
		
		croResource.addViewCount ( );
		return mapper.updateById ( croResource ) + mapper.addView ( resourceOperation ) == 2;
	}
	
	@Override
	public boolean addDownloadCount( @Valid @NotNull ResourceOperation resourceOperation ) {
		CroResource croResource = mapper.selectById ( resourceOperation.getResourceId ( ) );
		if ( ObjectUtil.isNull ( croResource ) ) {
			return false;
		}
		
		croResource.addDownloadCount ( );
		return mapper.updateById ( croResource ) + mapper.addDownload ( resourceOperation ) == 2;
	}
	
	@Override
	public boolean setEliteScore( @Valid @NotNull ResourceOperation resourceOperation ) {
		return false;
	}
	
	@Override
	@Transactional( readOnly = false, rollbackFor = GlobalException.class )
	@SystemLog
	public boolean setOrCancelFavorite( @Valid @NotNull ResourceOperation resourceOperation ) {
		long userId = resourceOperation.getUserId ( );
		long resourceId = resourceOperation.getResourceId ( );
		
		CroResource croResource = mapper.selectById ( resourceId );
		if ( ObjectUtil.isNull ( croResource ) ) return false;
		
		QueryWrapper<ResourceOperation> wrapper = new QueryWrapper<> ( );
		wrapper.eq ( "resource_id" , userId ).eq ( "resource_id" , resourceId );
		List<ResourceOperation> list = mapper.selectFavorites ( wrapper );
		if ( ObjectUtil.isNull ( list ) || list.size ( ) <= 0 ) {
			croResource.addFavoriteCount ( );
			return mapper.updateById ( croResource ) + mapper.addFavorite ( resourceOperation ) == 2;
		} else {
			croResource.deleteFavoriteCount ( );
			return mapper.updateById ( croResource ) + mapper.deleteFavorite ( resourceOperation ) == 2;
		}
	}
	
	@Override
	@Transactional( readOnly = false, rollbackFor = GlobalException.class )
	public boolean setStar( @Valid @NotNull ResourceOperation resourceOperation ) {
		long userId = resourceOperation.getUserId ( );
		long resourceId = resourceOperation.getResourceId ( );
		
		CroResource croResource = mapper.selectById ( resourceId );
		if ( ObjectUtil.isNull ( croResource ) ) return false;
		
		croResource.addStarCount ( resourceOperation.getOperIntResult ( ) );
		return mapper.updateById ( croResource ) + mapper.addStar ( resourceOperation ) == 2;
	}
	
	@Override
	public ResourceOperationVoList findStars( @Valid long resourceId ) {
		QueryWrapper<ResourceOperation> wrapper = new QueryWrapper<> ( );
		wrapper.eq ( "resource_id" , resourceId );
		List<ResourceOperation> results = mapper.selectStars ( wrapper );
		List<ResourceOperationVo> voList= new ArrayList<> (  );
		
		for ( ResourceOperation item : results ) {
			ResourceOperationVo vo = IResourceScoreVoMapper.INSTANCE.mapFrom (item);
			voList.add ( vo );
		}
		
		ResourceOperationVoList scoreVoList=new ResourceOperationVoList ();
		scoreVoList.setListData ( voList );
		
		return scoreVoList;
	}
	
}
