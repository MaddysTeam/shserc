package com.dianda.shsedu.controller;

import com.dianda.common.common.Constant;
import com.dianda.common.util.basic.DateTimeUtil;
import com.dianda.common.util.json.JsonResult;
import com.dianda.shsedu.bean.UserSelectParams;
import com.dianda.shsedu.dto.EditUserDto;
import com.dianda.shsedu.entity.ShseduUser;
import com.dianda.shsedu.service.IUserService;
import com.dianda.shsedu.vo.UserVo;
import com.dianda.shsedu.vo.UserVoList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/shseduUser")
public class ShseduUserController extends BaseController {

	@Resource
	IUserService service;

	@RequestMapping( path = "/list", method = RequestMethod.POST )
	public JsonResult findByPhrase(@RequestBody UserSelectParams params ) {
		UserVoList userVoList = service.findByPhrase ( params );
		return JsonResult.success ( userVoList );
	}

	@RequestMapping( path = "/{id}", method = RequestMethod.POST )
	public JsonResult findById( @PathVariable long id ) {
		UserVo userVo = service.findById ( id );
		return JsonResult.success ( userVo );
	}

	@PostMapping( "/edit" )
	public JsonResult edit(@Valid @RequestBody EditUserDto editUserDto , BindingResult bindingResult ) {
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ( generateErrorMessage ( bindingResult ) );
		}

		ShseduUser loginUser=super.getLoginUserInfo ();
		editUserDto.setOperatorId ( loginUser.getId () );
		editUserDto.setOperateDate ( DateTimeUtil.now () );

		return service.edit ( editUserDto ) ?
				JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
	}

//	@PostMapping( "/role/edit" )
//	public JsonResult editUserRole(@RequestBody @Valid EditUserRoleDto model , BindingResult bindingResult ) {
//		if ( bindingResult.hasErrors ( ) ) {
//			return JsonResult.error ( generateErrorMessage ( bindingResult ) );
//		}
//
//		return service.editUserRole ( model ) ?
//				JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
//				JsonResult.error ( Constant.Error.EDIT_FAILURE );
//	}

}
