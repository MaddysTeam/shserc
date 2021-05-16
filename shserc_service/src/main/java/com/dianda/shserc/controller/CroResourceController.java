package com.dianda.shserc.controller;

import com.dianda.common.util.json.JsonResult;
import com.dianda.shserc.bean.CroResourceSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditCroResourceDto;
import com.dianda.shserc.dto.ResourceAuditDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.service.ICroResourceService;
import com.dianda.shserc.vo.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/croResource")
public class CroResourceController extends BaseController {
	
	@Resource
	ICroResourceService service;
	
	@PostMapping( path = "/list" )
	public JsonResult findByPhrase( @RequestBody CroResourceSelectParams params ) {
		CroResourceVoList list = service.find ( params );
		return JsonResult.success ( list );
	}
	
	@PostMapping( path = "/{id}" )
	public JsonResult findById( @PathVariable long id ) {
		CroResourceVo o = service.findById ( id );
		return JsonResult.success ( o );
	}
	
	@PostMapping( path = "/edit" )
	public JsonResult edit( @RequestBody @Valid EditCroResourceDto editCroResourceDto , BindingResult bindingResult ) {
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ( super.generateErrorMessage ( bindingResult ) );
		}
		
		ResUser loginUser = super.getLoginUserInfo ( );
		editCroResourceDto.setOperatorId ( loginUser.getId ( ) );
		editCroResourceDto.setOperateDate ( LocalDateTime.now ( ) );
		
		boolean result = service.edit ( editCroResourceDto );
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
	
	@PostMapping( path = "/star/{id}/{score}" )
	public JsonResult star( @PathVariable long id ,@PathVariable int score ) {
		ResUser loginUser = getLoginUserInfo ( );
		ResourceOperation operation = new ResourceOperation ( );
		operation.setResourceId ( id );
		operation.setUserId ( loginUser.getId ( ) );
		operation.setOperIntResult ( score );
		operation.setAddTime ( LocalDateTime.now () );
		
		boolean result = service.setStar ( operation );
		return result ? JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
	}
	
	@PostMapping( path = "/stars/{id}" )
	public JsonResult findStars( @PathVariable @Min( value = 1 ) long id ) {
		ResourceOperationVoList list = service.findStars ( id );
		return JsonResult.success ( list );
	}
	
	@PostMapping( path = "/viewCount/{id}" )
	public JsonResult AddViewCount( @PathVariable @Min( value = 1 ) long id ){
		 ResUser loginUser=getLoginUserInfo ();
		ResourceOperation operation = new ResourceOperation ( );
		operation.setResourceId ( id );
		operation.setUserId ( loginUser.getId ( ) );
		operation.setAddTime ( LocalDateTime.now () );
		
		boolean result=service.addViewCount ( operation );
		
		return result ? JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
	}
	
}
