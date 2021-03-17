package com.dianda.shserc.controller;


import com.alibaba.fastjson.JSON;
import com.dianda.shserc.bean.RoleSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditRoleDto;
import com.dianda.shserc.entity.ResRole;
import com.dianda.shserc.service.IResRoleService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.ResRoleVo;
import com.dianda.shserc.vo.ResRoleVoList;
import com.dianda.shserc.vo.ResourceVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * role controller
 * @author huachao
 * @since 2020-08-12
 */
@RestController
@RequestMapping("/auth/role")
public class ResRoleController extends BaseController {

	@Resource
	IResRoleService service;

	@PostMapping(path = "list")
	public JsonResult findByPhrase(@RequestBody RoleSelectParams params, BindingResult bindingResult) {
		ResRoleVoList resRoleVoList = service.find(params);
		return JsonResult.success(resRoleVoList);
	}

	@PostMapping(path = "edit")
	public JsonResult edit(@RequestBody @Valid EditRoleDto editRoleDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return JsonResult.error(generateErrorMessage(bindingResult));
		}

		return service.edit(editRoleDto) ?
				JsonResult.success(Constant.Success.EDIT_SUCCESS) :
				JsonResult.error(Constant.Error.EDIT_FAILURE);
	}

	@PostMapping(path = "/{id}")
	public JsonResult findById(@PathVariable long id) {
		ResRoleVo resRoleVo = service.findById(id);
		return JsonResult.success(resRoleVo);
	}

}