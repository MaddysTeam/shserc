package com.dianda.shsedu.controller;

import com.dianda.common.common.Constant;
import com.dianda.common.util.json.JsonResult;
import com.dianda.shsedu.bean.ColumnSelectParam;
import com.dianda.shsedu.dto.EditColumnDto;
import com.dianda.shsedu.entity.ShseduUser;
import com.dianda.shsedu.service.IColumnService;
import com.dianda.shsedu.vo.ColumnVo;
import com.dianda.shsedu.vo.ColumnVoList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("ShseduColumn")
public class ColumnController extends BaseController {

	@Resource
	IColumnService columnService;

	@PostMapping(path = "/list")
	public JsonResult findByPhrase(@RequestBody ColumnSelectParam params, BindingResult bindingResult) {
		ColumnVoList columnVoList = columnService.findByPhrase(params);
		return JsonResult.success(columnVoList);
	}

	@PostMapping(path = "/edit")
	public JsonResult edit(@RequestBody @Valid EditColumnDto editColumnDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return JsonResult.error(generateErrorMessage(bindingResult));
		}

		ShseduUser loginUser= super.getLoginUserInfo ();
		editColumnDto.setOperatorId ( loginUser.getId () );

		return columnService.edit(editColumnDto) ?
				JsonResult.success(Constant.Success.EDIT_SUCCESS) :
				JsonResult.error(Constant.Error.EDIT_FAILURE);
	}

	@PostMapping(path = "/{id}")
	public JsonResult findById(@PathVariable long id) {
		ColumnVo columnVo = columnService.findById(id);
		return JsonResult.success(columnVo);
	}

}
