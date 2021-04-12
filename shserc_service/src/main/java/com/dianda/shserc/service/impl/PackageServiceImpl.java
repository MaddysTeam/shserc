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
import com.dianda.shserc.bean.PackageSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditPackageDto;
import com.dianda.shserc.dto.EditPackageResourceDto;
import com.dianda.shserc.dto.PackageAuditDto;
import com.dianda.shserc.dto.mappers.IEditPackageMapper;
import com.dianda.shserc.dto.mappers.IPackageAuditMapper;
import com.dianda.shserc.entity.Package;
import com.dianda.shserc.entity.PackageOperation;
import com.dianda.shserc.mapper.PackageMapper;
import com.dianda.shserc.service.IPackageService;
import com.dianda.shserc.vo.PackageVo;
import com.dianda.shserc.vo.PackageVoList;
import com.dianda.shserc.vo.mappers.IPackageVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PackageServiceImpl extends ServiceImpl<PackageMapper, Package> implements IPackageService {
	
	@Autowired
	PackageMapper mapper;
	
	DictionaryCache cache;
	
	@Autowired
	public PackageServiceImpl( DictionaryCache cache ) {
		this.cache = cache;
		this.cache.setCacheFromService ( 0 );
	}
	
	@Override
	public PackageVoList find( PackageSelectParams params ) {
		QueryWrapper<Package> wrapper = new QueryWrapper<> ( );
		long userId = params.getUserId ( );
		long deformityId = params.getDeformityId ( );
		long stateId = params.getStateId ( );
		long stageId = params.getStageId ( );
		long subjectId = params.getSubjectId ( );
		long domainId = params.getDomainId ( );
		long gradeId = params.getGradeId ( );
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
		if ( domainId > 0 ) {
			wrapper = wrapper.eq ( "domain_id" , domainId );
		}
		if ( gradeId > 0 ) {
			wrapper = wrapper.eq ( "grade_id" , stageId );
		}
		if ( ! StringUtil.isNullOrEmpty ( phrase ) )
			wrapper = wrapper.like ( "title" , phrase );
		
		// order phrase
		if ( ! ObjectUtil.isNull ( orderPhrases ) && orderPhrases.size ( ) > 0 ) {
			for ( Map.Entry<String, String> entry : orderPhrases.entrySet ( ) ) {
				String direction = entry.getValue ( );
				String orderPhrase = entry.getKey ( );
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
		IPage<Package> page = new Page<> ( current , size );
		page = mapper.selectPackages ( page , wrapper );
		List<Package> packages = page.getRecords ( );
		List<PackageVo> packageVoList = new ArrayList<> ( );
		
		// translate
		
		for ( Package pack : packages ) {
			Package.dictTranslate ( pack , cache ); // 翻译字典
			PackageVo vo = IPackageVoMapper.INSTANCE.mapFrom ( pack );
			vo.setGrade ( pack.getGrade ( ) );
			vo.setSubject ( pack.getSubject ( ) );
			packageVoList.add ( vo );
		}
		
		// get vo list data
		
		PackageVoList voList = new PackageVoList ( );
		voList.setCurrent ( current );
		voList.setSize ( size );
		voList.setTotal ( page.getTotal ( ) );

//		if (!ObjectUtil.isNull(resources) && resources.size() > 0)
		voList.setListData ( packageVoList );
		
		return voList;
	}
	
	@Override
	public PackageVo findById( long id ) {
		Package pack = mapper.selectById ( id );
		pack.dictTranslate ( pack , cache );
		PackageVo packageVo = IPackageVoMapper.INSTANCE.mapFrom ( pack );
		
		return packageVo;
	}
	
	@Override
	public boolean edit( @Valid @NotNull EditPackageDto editPackageDto ) {
		Package pack = IEditPackageMapper.INSTANCE.mapFrom ( editPackageDto );
		PackageVo packageVo = findById ( pack.getId ( ) );
		if ( pack.isNewOne ( ) && ObjectUtil.isNull ( packageVo ) ) {
			return mapper.insert ( pack ) > 0;
		} else {
			return mapper.updateById ( pack ) >= 0;
		}
	}
	
	@Override
	public boolean audit( @Valid PackageAuditDto packageAuditDto ) {
		Package pack = IPackageAuditMapper.INSTANCE.mapFrom ( packageAuditDto );
		pack.setStateId ( packageAuditDto.getAuditResult ( ) ? Constant.State.AUDITSUCCESS : Constant.State.AUDITFALURE );
		
		return mapper.updateById ( pack ) >= 0;
	}
	
	@Override
	@Transactional( readOnly = false, rollbackFor = GlobalException.class )
	public boolean addPackageResource( List<EditPackageResourceDto> editPackageResourceDto ) {
		return false;
	}
	
	@Override
	public boolean addViewCount( @Valid PackageOperation packageOperation ) {
		Package pack = mapper.selectById ( packageOperation.getPackageId ( ) );
		if ( ObjectUtil.isNull ( pack ) ) {
			return false;
		}
		
		pack.addViewCount ( );
		return mapper.updateById ( pack ) + mapper.addView ( packageOperation ) == 2;
	}
	
	@Override
	public boolean addDownloadCount( @Valid PackageOperation packageOperation ) {
		Package pack = mapper.selectById ( packageOperation.getResourceId ( ) );
		if ( ObjectUtil.isNull ( pack ) ) {
			return false;
		}
		
		pack.addDownloadCount ( );
		return mapper.updateById ( pack ) + mapper.addDownload ( packageOperation ) == 2;
	}
	
	@Override
	@SystemLog
	@Transactional( readOnly = false, rollbackFor = GlobalException.class )
	public boolean setOrCancelFavorite( @Valid PackageOperation packageOperation ) {
		long userId = packageOperation.getUserId ( );
		long packageId = packageOperation.getPackageId ( );
		
		Package pack = mapper.selectById ( packageId );
		if ( ObjectUtil.isNull ( pack ) ) return false;
		
		QueryWrapper<PackageOperation> wrapper = new QueryWrapper<> ( );
		wrapper.eq ( "user_id" , userId ).eq ( "package_id" , packageId );
		packageOperation = mapper.selectFavorite ( wrapper );
		if ( ObjectUtil.isNull ( packageOperation ) ) {
			pack.addFavoriteCount ( );
			
			return mapper.updateById ( pack ) + mapper.addFavorite ( packageOperation ) == 2;
		} else {
			pack.deleteFavoriteCount ( );
			
			return mapper.updateById ( pack ) + mapper.deleteFavorite ( packageOperation ) == 2;
		}
	}
	
	@Override
	public boolean setEliteScore( @Valid PackageOperation resourceOperation ) {
		return false;
	}
	
	@Override
	public boolean setStar( @Valid PackageOperation packageOperation ) {
		long userId = packageOperation.getUserId ( );
		long resourceId = packageOperation.getResourceId ( );
		
		Package pack = mapper.selectById ( resourceId );
		if ( ObjectUtil.isNull ( pack ) ) return false;
		
		pack.addStarCount ( packageOperation.getOperIntResult ( ) );
		return mapper.updateById ( pack ) + mapper.addStar ( packageOperation ) == 2;
	}
	
}
