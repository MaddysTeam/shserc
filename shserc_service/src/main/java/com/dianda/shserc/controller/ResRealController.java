package com.dianda.shserc.controller;

import com.dianda.shserc.bean.RealSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditRealDto;
import com.dianda.shserc.dto.RegistRealDto;
import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.service.IResRealService;
import com.dianda.shserc.util.json.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/real")
public class ResRealController {

	@Resource
	IResRealService service;

	@PostMapping("/register")
	public JsonResult register(@RequestBody RegistRealDto model){
		throw new GlobalException(Constant.ErrorCode.TODO,"");
	}

	@PostMapping("/list")
	public JsonResult findByPhrase(@RequestBody RealSelectParams params){
		throw new GlobalException(Constant.ErrorCode.TODO, "");
	}

	@PostMapping("/edit")
	public JsonResult edit(@RequestBody EditRealDto model){
		throw new GlobalException(Constant.ErrorCode.TODO, "");
	}

}
