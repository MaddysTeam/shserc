package com.dianda.shserc.controller;

import com.dianda.shserc.bean.ResourceSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.dto.ResourceAuditDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.service.IResourceService;
import com.dianda.common.util.json.JsonResult;
import com.dianda.shserc.vo.ResourceVo;
import com.dianda.shserc.vo.ResourceVoList;
import com.dianda.shserc.vo.ScoreVo;
import com.dianda.shserc.vo.ScoreVoList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * resource controller
 *
 * @author huachao
 * @since 2020-08-12
 */
@RestController
@RequestMapping( "/resource" )
public class ResourceController extends BaseController {
	
	@Resource
	IResourceService service;
	
	@PostMapping( path = "/list" )
	public JsonResult findByPhrase( @RequestBody ResourceSelectParams params ) {
		ResourceVoList list = service.find ( params );
		return JsonResult.success ( list );
	}
	
	@PostMapping( path = "/{id}" )
	public JsonResult findById( @PathVariable long id ) {
		ResourceVo o = service.findById ( id );
		return JsonResult.success ( o );
	}
	
	@PostMapping( path = "/edit" )
	public JsonResult edit( @RequestBody @Valid EditResourceDto editResourceDto , BindingResult bindingResult ) {
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ( super.generateErrorMessage ( bindingResult ) );
		}
		
		ResUser loginUser = super.getLoginUserInfo ( );
		editResourceDto.setOperatorId ( loginUser.getId ( ) );
		editResourceDto.setOperateDate ( LocalDateTime.now ( ) );
		
		boolean result = service.edit ( editResourceDto );
		return result ? JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
	}
	
	@PostMapping( path = "/audit" )
	public JsonResult audit( @RequestBody @Valid ResourceAuditDto resourceAuditDto , BindingResult bindingResult ) {
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ( super.generateErrorMessage ( bindingResult ) );
		}
		
		ResUser loginUser = getLoginUserInfo ( );
		resourceAuditDto.setAuditorId ( loginUser.getId ( ) );
		boolean result = service.audit ( resourceAuditDto );
		return result ? JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
	}
	
	
	@PostMapping( path = "/download/{id}" )
	public JsonResult download( @PathVariable long id ) {
		ResUser loginUser = getLoginUserInfo ( );
		boolean result = service.addDownloadCount ( new ResourceOperation ( id , loginUser.getId ( ) ) );
		return result ? JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
	}
	
	@PostMapping( path = "/favorite/{id}" )
	public JsonResult favorite( @PathVariable long id ) {
		ResUser loginUser = getLoginUserInfo ( );
		boolean result = service.setOrCancelFavorite ( new ResourceOperation ( id , loginUser.getId ( ) ) );
		return result ? JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
	}
	
	@PostMapping( path = "/star/{id}" )
	public JsonResult star( @PathVariable long id , int score ) {
		ResUser loginUser = getLoginUserInfo ( );
		ResourceOperation operation = new ResourceOperation ( );
		operation.setResourceId ( id );
		operation.setUserId ( loginUser.getId ( ) );
		operation.setOperIntResult ( score );
		
		boolean result = service.setStar ( operation );
		return result ? JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
	}
	
	@PostMapping( path = "/stars/{resourceId}" )
	public JsonResult findStars( @PathVariable @Min( value = 1 ) long resourceId , BindingResult bindingResult) {
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ( super.generateErrorMessage ( bindingResult ) );
		}
		
		ScoreVoList list = service.findStars ( resourceId );
		return JsonResult.success ( list );
	}
	
}
