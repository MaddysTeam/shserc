package com.dianda.shserc.controller;


import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditCompanyDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.service.IResCompanyService;
import com.dianda.common.util.json.JsonResult;
import com.dianda.shserc.vo.ResCompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * company controller
 *
 * @author huachao
 * @since 2020-08-17
 */
@RestController
@RequestMapping( "/company" )
public class CompanyController extends  BaseController {
	
	@Autowired
	IResCompanyService service;
	
	@RequestMapping(path="/search", method = RequestMethod.POST)
	public JsonResult search() {
		ResCompanyVo  vo= service.find ();
		return JsonResult.success (vo);
	}

	@RequestMapping(path="/children", method = RequestMethod.POST)
	public JsonResult searchChildren(@RequestParam String parentId){
		long pid=Long.parseLong ( parentId );
		ResCompanyVo  vo= service.findChildren (pid);
		return JsonResult.success (vo);
	}
	
	@RequestMapping(path="/edit", method = RequestMethod.POST)
	public JsonResult edit( @RequestBody @Valid EditCompanyDto companyDto , BindingResult bindingResult ) {
		ResUser user=getLoginUserInfo ();
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error (Constant.Error.PARAMS_IS_INVALID);
		}
		
		service.edit ( companyDto, user.getId () );
		
		return JsonResult.success ( Constant.Success.EDIT_SUCCESS );
	}
	
}

