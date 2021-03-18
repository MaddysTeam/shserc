package com.dianda.shserc.controller;

import com.alibaba.fastjson.JSON;
import com.dianda.shserc.bean.MenuSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditMenuDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.service.IMenuService;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.MenuVoList;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * menu controller
 *
 * @author huachao
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

	@Resource
	IMenuService menuService;

	@PostMapping("/list")
	public JsonResult findByPhrase(@RequestBody MenuSelectParams menuSelectParams) {
		MenuVoList menuVoList = menuService.find(menuSelectParams);

		return JsonResult.success(menuVoList);
	}

	@PostMapping("/edit")
	public JsonResult edit(@RequestBody @Valid EditMenuDto editMenuDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return JsonResult.error(super.generateErrorMessage(bindingResult));
		}

		if (super.checkIsUnAuthorized())
			return JsonResult.unAuthorized(Constant.Error.LOGIN_USER_INFO_INVALID);

		return menuService.edit(editMenuDto) ?
				JsonResult.success(Constant.Success.EDIT_SUCCESS) :
				JsonResult.error(Constant.Error.EDIT_FAILURE);
	}

}
