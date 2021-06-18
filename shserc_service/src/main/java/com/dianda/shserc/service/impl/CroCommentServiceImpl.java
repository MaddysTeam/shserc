package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.basic.StringUtil;
import com.dianda.common.validators.NotNull;
import com.dianda.shserc.bean.CommentSelectParams;
import com.dianda.common.common.Constant;
import com.dianda.shserc.dto.CommentAuditDto;
import com.dianda.shserc.dto.EditCommentDto;
import com.dianda.shserc.dto.mappers.IEditCroCommentMapper;
import com.dianda.shserc.entity.CroComment;
import com.dianda.shserc.entity.CroResource;
import com.dianda.shserc.mapper.CroCommentMapper;
import com.dianda.shserc.mapper.CroResourceMapper;
import com.dianda.shserc.service.ICroCommentService;
import com.dianda.shserc.vo.CommentVo;
import com.dianda.shserc.vo.CommentVoList;
import com.dianda.shserc.vo.mappers.ICroCommentVoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class CroCommentServiceImpl extends ServiceImpl<CroCommentMapper, CroComment> implements ICroCommentService {
	
	@Resource
	CroCommentMapper mapper;
	
	@Resource
	CroResourceMapper croResourceMapper;
	
	@Override
	public CommentVoList find( CommentSelectParams params ) {
		QueryWrapper<CroComment> queryWrapper = new QueryWrapper<> ( );
		long auditor = params.getAuditor ( );
		long resourceId = params.getResourceId ( );
		long stateId=params.getStateId ();
		String phrase = params.getSearchPhrase ( );

		// where phrase
		
		if ( resourceId > 0 ) {
			queryWrapper = queryWrapper.eq ( "resource_id" , resourceId );
		}
		if ( auditor > 0 ) {
			queryWrapper = queryWrapper.eq ( "auditor" , auditor );
		}
		if ( ! StringUtil.isNullOrEmpty ( phrase ) ) {
			queryWrapper = queryWrapper.and ( wrapper -> wrapper.like ( "content" , phrase ) );
		}
		if(stateId>0){
			queryWrapper = queryWrapper.eq ( "rc.state_id" , stateId );
		}
		
		// order phrase

		Map<String, String> orderPhrases = params.getOrderPhrases ( );
		if ( ! ObjectUtil.isNull ( orderPhrases ) && orderPhrases.size ( ) > 0 ) {
			for ( Map.Entry<String, String> entry : orderPhrases.entrySet ( ) ) {
				String direction = entry.getValue ( );
				String orderPhrase = entry.getKey ( );
				if ( direction.equals ( Constant.OrderDirection.ASC ) ) {
					queryWrapper = queryWrapper.orderByAsc ( orderPhrase );
				} else if ( direction.equals ( Constant.OrderDirection.DESC ) ) {
					queryWrapper = queryWrapper.orderByDesc ( orderPhrase );
				}
			}
		}
		
		// get paged list data
		
		int current = params.getCurrent ( );
		int size = params.getSize ( );
		IPage<CroComment> page = new Page<> ( current , size );
		page = mapper.selectCroComments ( page , queryWrapper );
		List<CroComment> listData = page.getRecords ( );
		List<CommentVo> listVoData = new ArrayList<> ( );
		for ( CroComment model : listData ) {
			listVoData.add ( ICroCommentVoMapper.INSTANCE.mapFrom ( model ) );
		}
		
		CommentVoList voList = new CommentVoList ( );
		voList.setListData ( listVoData );
		voList.setCurrent ( current );
		voList.setSize ( size );
		voList.setTotal ( page.getTotal ( ) );
		
		return voList;
		
	}
	
	@Override
	public boolean edit(@Valid @NotNull EditCommentDto model ) {
		if (ObjectUtil.isNull(model)) {
			throw new GlobalException (Constant.ErrorCode.PARAM_NULL_POINT_REFERENCE, Constant.Error.OBJECT_IS_REQUIRED);
		}
		
		int result = 0;
		CroComment CroComment = IEditCroCommentMapper.INSTANCE.mapFrom(model);
		CroComment.setAddUser ( model.getOperatorId () );
		CroComment.setAddTime ( model.getOperateDate () );
		//CroComment.setAuditType (  );
		
		if (CroComment.isNewOne() ) {
			result = mapper.insert(CroComment);
			return result > 0;
		} else {
			CroComment.setUpdateUser ( model.getOperatorId () );
			CroComment.setUpdateTime ( model.getOperateDate () );
			
			result = mapper.updateById(CroComment);
			return result >= 0;
		}
	}
	
	@Override
	public boolean audit( @Valid CommentAuditDto model ) {
		
//		CroComment comment=null;
//		comment.setStateId ( model.getAuditResult ( ) ? Constant.State.AUDITSUCCESS : Constant.State.AUDITFALURE );
		
		if(model.getAuditResult ( ) ) {
			CroResource croResource = croResourceMapper.selectById ( model.getResourceId ( ) );
			croResource.addCommentCount ( );
			croResourceMapper.updateById ( croResource );
		}
		
		return false;
	}
	
}
