package com.dianda.shserc.controller;

import com.dianda.shserc.bean.MenuSelectParams;
import com.dianda.common.common.Constant;
import com.dianda.shserc.dto.EditMenuDto;
import com.dianda.shserc.dto.EditMenuRoleDto;
import com.dianda.shserc.dto.EditStateDto;
import com.dianda.shserc.service.IMenuService;
import com.dianda.common.util.json.JsonResult;
import com.dianda.shserc.vo.MenuVoList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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
	IMenuService service;

	@PostMapping(path="/list")
	public JsonResult findByPhrase(@RequestBody MenuSelectParams menuSelectParams) {
		MenuVoList menuVoList = service.find(menuSelectParams);

		return JsonResult.success(menuVoList);
	}

	@PostMapping(path="/edit")
	public JsonResult edit(@RequestBody @Valid EditMenuDto editMenuDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return JsonResult.error(super.generateErrorMessage(bindingResult));
		}
		
		return service.edit(editMenuDto) ?
				JsonResult.success(Constant.Success.EDIT_SUCCESS) :
				JsonResult.error(Constant.Error.EDIT_FAILURE);
	}

	@PostMapping(path="/state/edit")
	public JsonResult state(@RequestBody @Valid EditStateDto editStateDto,BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return JsonResult.error(super.generateErrorMessage(bindingResult));
		}

		return service.setState(editStateDto)?
				JsonResult.success(Constant.Success.EDIT_SUCCESS) :
				JsonResult.error(Constant.Error.EDIT_FAILURE);
	}

	@PostMapping(path="/role/edit")
	public JsonResult editMenuRole( @RequestBody() List<EditMenuRoleDto> editMenuRoleDto, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
       	return JsonResult.error(super.generateErrorMessage(bindingResult));
	   }
       
       return  service.editMenuRole ( editMenuRoleDto )?
			   JsonResult.success(Constant.Success.EDIT_SUCCESS) :
			   JsonResult.error(Constant.Error.EDIT_FAILURE);
	}
}
