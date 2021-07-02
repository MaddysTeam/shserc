package com.dianda.shsedu.controller;

import com.dianda.common.common.Constant;
import com.dianda.common.util.json.JsonResult;
import com.dianda.shsedu.bean.RoleSelectParams;
import com.dianda.shsedu.dto.EditRoleDto;
import com.dianda.shsedu.entity.ShseduUser;
import com.dianda.shsedu.service.IRoleService;
import com.dianda.shsedu.vo.RoleVo;
import com.dianda.shsedu.vo.RoleVoList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("shseduRole")
public class ShseduRoleController extends  BaseController {
	@Resource
	IRoleService service;

	@PostMapping(path = "/list")
	public JsonResult findByPhrase(@RequestBody RoleSelectParams params, BindingResult bindingResult) {
		RoleVoList resRoleVoList = service.findByPhrase(params);
		return JsonResult.success(resRoleVoList);
	}

	@PostMapping(path = "/edit")
	public JsonResult edit(@RequestBody @Valid EditRoleDto editRoleDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return JsonResult.error(generateErrorMessage(bindingResult));
		}

		ShseduUser loginUser= super.getLoginUserInfo ();
		editRoleDto.setOperatorId ( loginUser.getId () );

		return service.edit(editRoleDto) ?
				JsonResult.success(Constant.Success.EDIT_SUCCESS) :
				JsonResult.error(Constant.Error.EDIT_FAILURE);
	}

	@PostMapping(path = "/{id}")
	public JsonResult findById(@PathVariable long id) {
		RoleVo roleVo = service.findById(id);
		return JsonResult.success(roleVo);
	}
}
