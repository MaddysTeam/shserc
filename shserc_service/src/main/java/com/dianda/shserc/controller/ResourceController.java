package com.dianda.shserc.controller;

import com.dianda.shserc.bean.ResourceSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.service.IResourceService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.util.logger.system.SystemLog;
import com.dianda.shserc.vo.ResourceVo;
import com.dianda.shserc.vo.ResourceVoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/resource")
public class ResourceController {

	@Resource
	IResourceService service;

	@PostMapping(path = "/list")
	public JsonResult findByPhrase(@RequestBody ResourceSelectParams params) {
		ResourceVoList list = service.find(params);
		return JsonResult.success(list);
	}

	@PostMapping(path = "/{id}")
	public JsonResult findById(@PathVariable long id) {
		ResourceVo o = service.getById(id);
		return JsonResult.success(o);
	}

	@PostMapping(path = "/edit")
	public JsonResult edit(@RequestBody @Valid EditResourceDto resourceDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return JsonResult.error(Constant.Error.PARAMS_IS_INVALID);
		}

		service.edit(resourceDto);
		return JsonResult.success(Constant.Success.EDIT_SUCCESS);
	}

	@PostMapping(path="/download/{id}")
	public JsonResult download(@PathVariable  long id){
		return null;
	}

	@PostMapping(path="/download/{id}")
	public JsonResult favorite(@PathVariable long id){
		return null;
	}

	@PostMapping(path="/star/{id}")
	public JsonResult start(@PathVariable long id, int score){
		return null;
	}

}
