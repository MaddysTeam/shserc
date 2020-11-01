package com.dianda.shserc.controller;

import com.dianda.shserc.bean.ResourceSelectParams;
import com.dianda.shserc.service.IResourceService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.ResourceVo;
import com.dianda.shserc.vo.ResourceVoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping( "/resource" )
public class ResourceController {
	
	@Resource
	IResourceService service;
	
	@PostMapping(path="/list")
	public JsonResult findByPhrase( @RequestBody ResourceSelectParams params ) {
		ResourceVoList list = service.find ( params );
		return JsonResult.success ( list );
	}
	
}
