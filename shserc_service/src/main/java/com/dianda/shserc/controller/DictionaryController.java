package com.dianda.shserc.controller;


import com.dianda.shserc.service.IDictionaryService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.DictionaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huachao
 * @since 2020-10-05
 */
@RestController
@RequestMapping( "/dictionary" )
public class DictionaryController {
	
	@Autowired
	IDictionaryService service;
	
	@RequestMapping(path = "/search",method = RequestMethod.POST )
	public JsonResult search(){
		DictionaryVo vo = service.findAll ();
		return JsonResult.success ( vo );
	}
	

}

