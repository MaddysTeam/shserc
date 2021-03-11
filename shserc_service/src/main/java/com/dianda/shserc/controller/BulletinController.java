package com.dianda.shserc.controller;


import com.dianda.shserc.bean.BulletinSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditBulletinDto;
import com.dianda.shserc.dto.mappers.IEditBulletinMapper;
import com.dianda.shserc.entity.Bulletin;
import com.dianda.shserc.service.IBulletinService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.BulletinVo;
import com.dianda.shserc.vo.BulletinVoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping( "/bulletin" )
public class BulletinController extends BaseController {
	
	@Resource
	IBulletinService service;
	
	@PostMapping( "/list" )
	public JsonResult findByParam( BulletinSelectParams params ) {
		BulletinVoList bulletinVoList = service.find ( params );
		return JsonResult.success ( bulletinVoList );
	}
	
	@PostMapping( path = "/{id}" )
	public JsonResult findById( @PathVariable long id ) {
		BulletinVo bulletinVo = service.findById ( id );
		return JsonResult.success ( bulletinVo );
	}
	
	@PostMapping( path = "edit" )
	public JsonResult edit( @Valid @RequestBody EditBulletinDto editBulletinDto , BindingResult bindingResult ) {
		if ( bindingResult.hasErrors ( ) ) {
			String errorMessage = super.generateErrorMessage ( bindingResult );
			return JsonResult.error ( errorMessage );
		}
		
		boolean result=service.edit ( editBulletinDto );
		return result ? JsonResult.success( Constant.Success.EDIT_SUCCESS) :
				JsonResult.error(Constant.Error.EDIT_FAILURE);
	}
	
	
}
