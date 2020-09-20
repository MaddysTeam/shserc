package com.dianda.shserc.controller;


import com.dianda.shserc.bean.UserSelectParams;
import com.dianda.shserc.service.IResUserService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.ResUserVo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制器
 * @author huachao
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/user")
public class ResUserController {

	@Resource
	IResUserService resUserService;

	@RequestMapping( path ="/list", method = RequestMethod.POST)
	public JsonResult findByPhrase(@RequestBody UserSelectParams params) {
		
		
		ResUserVo vo = resUserService.find(params);

		return JsonResult.success(vo,"");
	}

}

