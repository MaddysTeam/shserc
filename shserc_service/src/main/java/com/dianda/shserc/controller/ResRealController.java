package com.dianda.shserc.controller;

import com.dianda.shserc.bean.RealSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditRealDto;
import com.dianda.shserc.dto.RegistRealDto;
import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.service.IResRealService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.ResRealVoList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/real")
public class ResRealController {

	@Resource
	IResRealService service;

	@PostMapping("/register")
	public JsonResult register(@RequestBody RegistRealDto model,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return JsonResult.error(Constant.Error.PARAMS_IS_INVALID);
		}

		return null;
	}

	@PostMapping("/list")
	public JsonResult findByPhrase(@RequestBody RealSelectParams params) {
		ResRealVoList list= service.find(params);
		return  JsonResult.success(list);
	}

	@PostMapping("/edit")
	public JsonResult edit(@Valid @RequestBody EditRealDto model, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return JsonResult.error(Constant.Error.PARAMS_IS_INVALID);
		}

		return service.edit(model) ?
				JsonResult.success(Constant.Success.EDIT_SUCCESS) :
				JsonResult.error(Constant.Error.EDIT_FAILURE);
	}

}
