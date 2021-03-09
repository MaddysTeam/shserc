package com.dianda.shserc.controller;


import com.dianda.shserc.bean.UserSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditUserDto;
import com.dianda.shserc.service.IResUserService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.ResUserVo;
import com.dianda.shserc.vo.ResUserVoList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户控制器
 *
 * @author huachao
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/user")
public class ResUserController extends BaseController {

	@Resource
	IResUserService service;

	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public JsonResult findByPhrase(@RequestBody UserSelectParams params) {
		ResUserVoList resUserVoList = service.find(params);
		return JsonResult.success(resUserVoList);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.POST)
	public JsonResult findById(@PathVariable long id) {
		ResUserVo resUserVo = service.getById(id);
		return JsonResult.success(resUserVo);
	}

	@PostMapping("/edit")
	public JsonResult edit(@Valid @RequestBody EditUserDto model , BindingResult bindingResult ){
		if(bindingResult.hasErrors()){
			return JsonResult.error ( generateErrorMessage(bindingResult));
		}

		return service.edit ( model ) ?
				JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
	}

}

