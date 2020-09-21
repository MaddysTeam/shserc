package com.dianda.shserc.controller;


import com.dianda.shserc.dto.EditCompanyDto;
import com.dianda.shserc.service.IResCompanyService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.ResCompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * company controller
 *
 * @author huachao
 * @since 2020-08-17
 */
@RestController
@RequestMapping( "/company" )
public class ResCompanyController {
	
	@Autowired
	IResCompanyService service;
	
	@RequestMapping(path="/search", method = RequestMethod.POST)
	public JsonResult search() {
		ResCompanyVo  vo= service.find ();
		return JsonResult.success (vo);
	}

	@RequestMapping(path="/children", method = RequestMethod.POST)
	public JsonResult searchChildren(@RequestBody long parentId){
		ResCompanyVo  vo= service.findChildren (parentId);
		return JsonResult.success (vo);
	}
	
	@RequestMapping(path="/edit", method = RequestMethod.POST)
	public JsonResult edit( @RequestBody @Valid EditCompanyDto companyDto , BindingResult bindingResult ) {
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ("");
		}
		
		service.edit ( companyDto );
		
		return JsonResult.success ( "" );
	}
	
}

